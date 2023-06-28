package com.nuzhnov.testtask.di

import com.nuzhnov.testtask.data.database.AppDatabase
import com.nuzhnov.testtask.di.annotation.IoDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
internal object ProviderModule {
    @[Provides Singleton]
    fun provideCarDao(appDatabase: AppDatabase) = appDatabase.getCarDao()

    @[Provides Singleton]
    fun provideAppDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, AppDatabase::class.java, name = AppDatabase.NAME)
        .fallbackToDestructiveMigration()
        .build()

    @[Provides Singleton IoDispatcher]
    fun provideIoDispatcher() = Dispatchers.IO
}
