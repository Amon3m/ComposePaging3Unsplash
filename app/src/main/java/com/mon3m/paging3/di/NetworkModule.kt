package com.mon3m.paging3.di

import com.mon3m.paging3.data.local.LocalSource
import com.mon3m.paging3.data.remote.ApiClient
import com.mon3m.paging3.data.remote.ApiService
import com.mon3m.paging3.data.remote.RemoteSource
import com.mon3m.paging3.data.repo.Repository
import com.mon3m.paging3.utils.Constant.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {
        @Singleton
        @Provides
        fun provideHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        @Singleton
        @Provides
        fun provideRepository(remoteSource: RemoteSource, localSource: LocalSource): Repository {
            return Repository(localSource, remoteSource)
        }
    }

    @Binds
    @Singleton
    abstract fun provideRemoteSource(apiClient: ApiClient): RemoteSource
}
