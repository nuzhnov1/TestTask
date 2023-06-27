package com.nuzhnov.testtask.domen.repository

import com.nuzhnov.testtask.domen.models.Car
import com.nuzhnov.testtask.domen.models.SortType
import kotlinx.coroutines.flow.Flow

internal interface ICarRepository {
    fun getCarsFlow(sortType: SortType): Flow<List<Car>>
    fun getCarsByNumberFlow(number: String, sortType: SortType): Flow<List<Car>>
}
