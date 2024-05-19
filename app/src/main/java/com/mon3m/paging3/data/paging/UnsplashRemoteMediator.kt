package com.mon3m.paging3.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mon3m.paging3.data.local.LocalSource
import com.mon3m.paging3.data.remote.RemoteSource
import com.mon3m.paging3.model.UnsplashImage
import com.mon3m.paging3.model.UnsplashRemoteKeys
import com.mon3m.paging3.model.UserLinks
@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator (
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource
) : RemoteMediator<Int, UnsplashImage>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImage>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
            }

            val response = remoteSource.getPhotos(page = currentPage, perPage = ITEMS_PER_PAGE)
            Log.d("UnsplashRemoteMediator", "Fetched ${response.size} items from network")

            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            if (loadType == LoadType.REFRESH) {
                localSource.clearAllImages()
                localSource.clearRemoteKeys()
            }

            val keys = response.map { unsplashImage ->
                UnsplashRemoteKeys(
                    id = unsplashImage.id,
                    prevPage = prevPage,
                    nextPage = nextPage
                )
            }

            val images = response.map { unsplashImage ->
                unsplashImage.copy(
                    user = unsplashImage.user?.copy(
                        userLinks = unsplashImage.user.userLinks ?: UserLinks()
                    )
                )
            }

            localSource.insertAllRemoteKeys(keys)
            localSource.insertAll(images)
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.e("UnsplashRemoteMediator", "Error loading data", e)
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                localSource.getRemoteKeysId(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                localSource.getRemoteKeysId(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                localSource.getRemoteKeysId(id = unsplashImage.id)
            }
    }

    companion object {
        const val ITEMS_PER_PAGE = 10
    }
}
