package com.mon3m.paging3.di

import com.mon3m.paging3.data.repo.RepoInterface
import com.mon3m.paging3.data.repo.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {
    @Binds
    @ViewModelScoped
    abstract fun bindRepository(repository: Repository): RepoInterface
}
