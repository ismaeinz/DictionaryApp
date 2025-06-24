package com.example.dictionaryapp.app.di

import com.example.dictionaryapp.app.data.repositoryImpl.DictionaryRepositoryImpl
import com.example.dictionaryapp.app.domain.repository.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDictionaryRepository(
        dictionaryRepositoryImpl: DictionaryRepositoryImpl,
    ): DictionaryRepository
}