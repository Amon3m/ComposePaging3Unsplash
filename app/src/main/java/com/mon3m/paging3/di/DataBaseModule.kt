package com.mon3m.paging3.di

import android.content.Context
import androidx.room.Room
import com.mon3m.paging3.data.local.ConcreteLocalSource
import com.mon3m.paging3.data.local.ImageDao
import com.mon3m.paging3.data.local.LocalSource
import com.mon3m.paging3.data.local.RemoteKeyDao
import com.mon3m.paging3.data.local.UnsplashDataBase
import com.mon3m.paging3.utils.Constant.UNSPLASH_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): UnsplashDataBase {
        return Room.databaseBuilder(context, UnsplashDataBase::class.java, UNSPLASH_DATABASE_NAME).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideImageDao(unsplashDataBase: UnsplashDataBase): ImageDao {
        return unsplashDataBase.imageDao()
    }

    @Singleton
    @Provides
    fun provideKeyDao(unsplashDataBase: UnsplashDataBase): RemoteKeyDao {
        return unsplashDataBase.remoteKeyDao()
    }

    @Provides
    @Singleton
    fun provideLocalSource(
        imageDao: ImageDao,
        remoteKeyDao: RemoteKeyDao
    ): LocalSource {
        return ConcreteLocalSource(imageDao, remoteKeyDao)
    }
}
