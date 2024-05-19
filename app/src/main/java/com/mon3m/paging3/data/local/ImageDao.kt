package com.mon3m.paging3.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mon3m.paging3.model.UnsplashImage
import com.mon3m.paging3.utils.Constant.UNSPLASH_IMAGE_TABLE

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<UnsplashImage>)

    @Query("SELECT * FROM $UNSPLASH_IMAGE_TABLE")
    fun getAllImages(): PagingSource<Int,UnsplashImage>

    @Query("DELETE FROM $UNSPLASH_IMAGE_TABLE")
    suspend fun clearAllImages()
}