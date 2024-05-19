package com.mon3m.paging3.data.local

import androidx.paging.PagingSource
import com.mon3m.paging3.model.UnsplashImage
import com.mon3m.paging3.model.UnsplashRemoteKeys
import javax.inject.Inject

class ConcreteLocalSource @Inject constructor(
    private val imageDao: ImageDao,
    private val remoteKeyDao: RemoteKeyDao
) : LocalSource {

    override suspend fun insertAll(images: List<UnsplashImage>) {
        imageDao.insertAll(images)
    }


    override suspend fun insertAllRemoteKeys(remoteKey: List<UnsplashRemoteKeys>) {
        remoteKeyDao.insertAll(remoteKey)
    }

    override fun getAllImages(): PagingSource<Int, UnsplashImage> {
        return imageDao.getAllImages()
    }

    override suspend fun clearAllImages() {
        remoteKeyDao.clearRemoteKeys()
    }


    override suspend fun getRemoteKeysId(id: String): UnsplashRemoteKeys? {
        return remoteKeyDao.remoteKeysId(id)

    }

    override suspend fun clearRemoteKeys() {

        remoteKeyDao.clearRemoteKeys()
    }
}