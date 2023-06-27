package com.nuzhnov.testtask.data.repository

import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.data.datasource.CarsLocalDataSource
import com.nuzhnov.testtask.data.mapper.toCar
import com.nuzhnov.testtask.data.mapper.toCarEntityField
import com.nuzhnov.testtask.di.annotations.IoDispatcher
import com.nuzhnov.testtask.domen.models.SortType
import com.nuzhnov.testtask.domen.repository.ICarRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CarsRepositoryImpl @Inject constructor(
    private val carsLocalDataSource: CarsLocalDataSource,
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher
) : ICarRepository {

    override fun getCarsFlow(sortType: SortType) = carsLocalDataSource
        .getCarEntitiesFlow(sortField = sortType.toCarEntityField())
        .map { entityList -> entityList.map(CarEntity::toCar) }
        .flowOn(context = coroutineDispatcher)

    override fun getCarsByNumberFlow(number: String, sortType: SortType) = carsLocalDataSource
        .getCarEntitiesByNumber(number, sortField = sortType.toCarEntityField())
        .map { entityList -> entityList.map(CarEntity::toCar) }
        .flowOn(context = coroutineDispatcher)
}
