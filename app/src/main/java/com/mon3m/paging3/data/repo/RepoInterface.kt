package com.mon3m.paging3.data.repo

import androidx.paging.PagingData
import com.mon3m.paging3.model.UnsplashImage
import kotlinx.coroutines.flow.Flow

interface RepoInterface {
    fun getAllImages(): Flow<PagingData<UnsplashImage>>
}