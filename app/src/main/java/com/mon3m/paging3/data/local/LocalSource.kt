package com.mon3m.paging3.data.local

import androidx.paging.PagingSource
import com.mon3m.paging3.model.UnsplashImage
import com.mon3m.paging3.model.UnsplashRemoteKeys

interface LocalSource {
    suspend fun insertAll(images: List<UnsplashImage>)
    fun getAllImages(): PagingSource<Int, UnsplashImage>
    suspend fun clearAllImages()
    suspend fun insertAllRemoteKeys(remoteKey: List<UnsplashRemoteKeys>)
    suspend fun getRemoteKeysId(id: String): UnsplashRemoteKeys?
    suspend fun clearRemoteKeys()
}