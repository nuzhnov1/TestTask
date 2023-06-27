package com.nuzhnov.testtask.domen.usecase

import com.nuzhnov.testtask.domen.model.CarSortType
import com.nuzhnov.testtask.domen.model.CarSortType.NONE
import com.nuzhnov.testtask.domen.repository.CarRepository
import javax.inject.Inject

class GetCarsByNumberFlowUseCase @Inject internal constructor(
    private val repository: CarRepository
) {
    operator fun invoke(number: String, sortType: CarSortType = NONE) =
        repository.getCarsByNumberFlow(number, sortType)
}
