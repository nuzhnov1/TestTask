package com.nuzhnov.testtask.domen.repository

import com.nuzhnov.testtask.domen.model.Car
import com.nuzhnov.testtask.domen.model.CarSortType
import com.nuzhnov.testtask.domen.model.SortOrder
import kotlinx.coroutines.flow.Flow

internal interface CarRepository {
    fun getCarsFlow(
        number: String,
        sortType: CarSortType,
        sortOrder: SortOrder
    ): Flow<List<Car>>
}
