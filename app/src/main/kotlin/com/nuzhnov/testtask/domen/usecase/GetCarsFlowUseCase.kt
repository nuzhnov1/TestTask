package com.nuzhnov.testtask.domen.usecase

import com.nuzhnov.testtask.domen.model.CarSortType
import com.nuzhnov.testtask.domen.model.SortOrder
import com.nuzhnov.testtask.domen.repository.CarRepository
import javax.inject.Inject

class GetCarsFlowUseCase @Inject internal constructor(
    private val repository: CarRepository
) {
    operator fun invoke(number: String, sortType: CarSortType, sortOrder: SortOrder) =
        repository.getCarsFlow(number, sortType, sortOrder)
}
