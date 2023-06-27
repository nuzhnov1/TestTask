package com.nuzhnov.testtask.di

import com.nuzhnov.testtask.data.repository.CarsRepositoryImpl
import com.nuzhnov.testtask.domen.repository.CarRepository
import javax.inject.Singleton
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
internal interface BinderModule {
    @[Binds Singleton]
    fun bindRepository(instance: CarsRepositoryImpl): CarRepository
}
