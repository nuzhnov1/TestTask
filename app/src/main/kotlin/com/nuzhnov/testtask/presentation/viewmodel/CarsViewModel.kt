package com.nuzhnov.testtask.presentation.viewmodel

import com.nuzhnov.testtask.domen.model.Car
import com.nuzhnov.testtask.domen.model.CarSortType
import com.nuzhnov.testtask.domen.model.SortOrder
import com.nuzhnov.testtask.domen.usecase.GetCarsFlowUseCase
import com.nuzhnov.testtask.presentation.mapper.toUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
internal class CarsViewModel @Inject constructor(
    private val getCarsFlowUseCase: GetCarsFlowUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val carNumberStateFlow = savedStateHandle.getStateFlow(
        key = CAR_NUMBER_KEY,
        initialValue = EMPTY_CAR_NUMBER
    )

    val carSortTypeStateFlow = savedStateHandle.getStateFlow(
        key = CAR_SORT_TYPE_KEY,
        initialValue = DEFAULT_CAR_SORT_TYPE
    )

    val carSortOrderStateFlow = savedStateHandle.getStateFlow(
        key = CAR_SORT_ORDER_KEY,
        initialValue = DEFAULT_CAR_SORT_ORDER
    )

    @OptIn(ExperimentalCoroutinesApi::class) val carsListFlow = combine(
        carNumberStateFlow,
        carSortTypeStateFlow,
        carSortOrderStateFlow
    ) { number, sortType, sortOrder ->
        CarsRequest(number, sortType, sortOrder)
    }.transformLatest { request ->
        val (number, sortType, sortOrder) = request
        val uiModelsFlow = getCarsFlowUseCase(number, sortType, sortOrder)
            .map { modelsList -> modelsList.map(Car::toUiModel) }

        emitAll(uiModelsFlow)
    }


    fun setCarNumber(carNumber: String) {
        savedStateHandle[CAR_NUMBER_KEY] = carNumber
    }

    fun setCarSortType(sortType: CarSortType) {
        savedStateHandle[CAR_SORT_TYPE_KEY] = sortType
    }

    fun setCarSortOrder(sortOrder: SortOrder) {
        savedStateHandle[CAR_SORT_ORDER_KEY] = sortOrder
    }


    private data class CarsRequest(
        val number: String,
        val sortType: CarSortType,
        val sortOrder: SortOrder
    )

    private companion object {
        const val CAR_NUMBER_KEY = "CAR_NUMBER_KEY"
        const val CAR_SORT_TYPE_KEY = "CAR_SORT_TYPE_KEY"
        const val CAR_SORT_ORDER_KEY = "CAR_SORT_ORDER_KEY"
        const val EMPTY_CAR_NUMBER = ""

        val DEFAULT_CAR_SORT_TYPE = CarSortType.NUMBER
        val DEFAULT_CAR_SORT_ORDER = SortOrder.ASC
    }
}
