package com.mon3m.paging3.data.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mon3m.paging3.data.local.LocalSource
import com.mon3m.paging3.data.paging.UnsplashRemoteMediator
import com.mon3m.paging3.data.remote.RemoteSource
import com.mon3m.paging3.model.UnsplashImage
import com.mon3m.paging3.utils.Constant.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : RepoInterface{

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { localSource.getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(
                remoteSource = remoteSource,
                localSource = localSource
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}