package com.mon3m.paging3.data.remote

import com.mon3m.paging3.model.UnsplashImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ApiClient @Inject constructor(private val apiService: ApiService) : RemoteSource {


    override suspend fun getPhotos(page: Int, perPage: Int): List<UnsplashImage> {
        return apiService.getPhotos(page, perPage)
    }

}