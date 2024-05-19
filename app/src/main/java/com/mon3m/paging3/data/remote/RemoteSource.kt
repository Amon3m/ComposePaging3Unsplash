package com.mon3m.paging3.data.remote

import com.mon3m.paging3.model.UnsplashImage

interface RemoteSource {


    suspend fun getPhotos(
        page: Int,
        perPage: Int
    ): List<UnsplashImage>
}
