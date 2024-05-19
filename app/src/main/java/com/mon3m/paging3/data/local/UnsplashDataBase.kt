package com.mon3m.paging3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mon3m.paging3.model.UnsplashImage
import com.mon3m.paging3.model.UnsplashRemoteKeys

@Database(entities = [UnsplashImage::class,UnsplashRemoteKeys::class ], version = 20)
abstract class UnsplashDataBase:RoomDatabase() {

    abstract fun imageDao(): ImageDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}