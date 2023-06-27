package com.nuzhnov.testtask.domen.repository

import com.nuzhnov.testtask.domen.model.Car
import com.nuzhnov.testtask.domen.model.CarSortType
import kotlinx.coroutines.flow.Flow

internal interface CarRepository {
    fun getCarsFlow(sortType: CarSortType): Flow<List<Car>>
    fun getCarsByNumberFlow(number: String, sortType: CarSortType): Flow<List<Car>>
}
