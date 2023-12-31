package com.nuzhnov.testtask.data.repository

import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.data.datasource.CarsLocalDataSource
import com.nuzhnov.testtask.data.mapper.toModel
import com.nuzhnov.testtask.di.annotation.IoDispatcher
import com.nuzhnov.testtask.domen.model.CarSortType
import com.nuzhnov.testtask.domen.model.SortOrder
import com.nuzhnov.testtask.domen.repository.CarRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CarsRepositoryImpl @Inject constructor(
    private val carsLocalDataSource: CarsLocalDataSource,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : CarRepository {

    override fun getCarsFlow(
        number: String,
        sortType: CarSortType,
        sortOrder: SortOrder
    ) = carsLocalDataSource
        .getCarEntitiesFlow(number, sortType, sortOrder)
        .map { entityList -> entityList.map(CarEntity::toModel) }
        .flowOn(context = coroutineDispatcher)
}
