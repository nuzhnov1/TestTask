package com.nuzhnov.testtask.data.mapper

import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.domen.model.Car
import com.nuzhnov.testtask.domen.model.CarSortType
import com.nuzhnov.testtask.domen.model.SortOrder


internal fun CarEntity.toModel() = Car(
    number = number,
    model = model,
    releaseYear = releaseYear,
    millage = millage
)

internal fun CarSortType.toQueryString() = when (this) {
    CarSortType.NUMBER          -> CarEntity.NUMBER_FIELD_NAME
    CarSortType.MODEL           -> CarEntity.MODEL_FIELD_NAME
    CarSortType.RELEASE_YEAR    -> CarEntity.RELEASE_YEAR_FIELD_NAME
    CarSortType.MILLAGE         -> CarEntity.MILLAGE_FIELD_NAME
}

internal fun SortOrder.toQueryString() = when (this) {
    SortOrder.ASC   -> "ASC"
    SortOrder.DESC  -> "DESC"
}
