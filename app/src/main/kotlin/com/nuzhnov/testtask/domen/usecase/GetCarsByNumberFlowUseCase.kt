package com.nuzhnov.testtask.domen.usecase

import com.nuzhnov.testtask.domen.models.SortType
import com.nuzhnov.testtask.domen.models.SortType.NONE
import com.nuzhnov.testtask.domen.repository.ICarRepository
import javax.inject.Inject

class GetCarsByNumberFlowUseCase @Inject internal constructor(
    private val repository: ICarRepository
) {
    operator fun invoke(number: String, sortType: SortType = NONE) =
        repository.getCarsByNumberFlow(number, sortType)
}
