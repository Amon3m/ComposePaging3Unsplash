package com.mon3m.paging3.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mon3m.paging3.utils.Constant.UNSPLASH_IMAGE_TABLE

@Entity(tableName =UNSPLASH_IMAGE_TABLE)
data class UnsplashImage(
    @PrimaryKey
    val id:String,
    @Embedded
    val urls: Urls,
    val likes:Int,
    @Embedded
    val user:User?
)
