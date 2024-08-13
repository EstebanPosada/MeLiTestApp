package com.estebanposada.testapp.app.di

import com.estebanposada.testapp.data.repository.SearchRepositoryImpl
import com.estebanposada.testapp.data.source.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository
}