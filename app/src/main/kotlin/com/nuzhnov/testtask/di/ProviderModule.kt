package com.nuzhnov.testtask.di

import com.nuzhnov.testtask.di.annotations.IoDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
object ProviderModule {
    @[Provides Singleton IoDispatcher]
    fun provideIoDispatcher() = Dispatchers.IO
}
