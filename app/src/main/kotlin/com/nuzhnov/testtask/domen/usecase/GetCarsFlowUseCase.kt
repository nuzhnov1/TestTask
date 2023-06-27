package com.nuzhnov.testtask.domen.usecase

import com.nuzhnov.testtask.domen.models.SortType
import com.nuzhnov.testtask.domen.models.SortType.NONE
import com.nuzhnov.testtask.domen.repository.ICarRepository
import javax.inject.Inject

class GetCarsFlowUseCase @Inject internal constructor(
    private val repository: ICarRepository
) {
    operator fun invoke(sortType: SortType = NONE) = repository.getCarsFlow(sortType)
}
