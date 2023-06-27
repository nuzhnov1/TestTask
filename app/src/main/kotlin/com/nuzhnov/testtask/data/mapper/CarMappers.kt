package com.nuzhnov.testtask.data.mapper

import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.domen.models.Car
import com.nuzhnov.testtask.domen.models.SortType

internal fun CarEntity.toCar() = Car(
    number = number,
    model = model,
    releaseYear = releaseYear,
    millage = millage
)

internal fun SortType.toCarEntityField() = when (this) {
    SortType.NONE           -> null
    SortType.NUMBER         -> CarEntity.NUMBER_FIELD_NAME
    SortType.MODEL          -> CarEntity.MODEL_FIELD_NAME
    SortType.RELEASE_YEAR   -> CarEntity.RELEASE_YEAR_FIELD_NAME
    SortType.MILLAGE        -> CarEntity.MILLAGE_FIELD_NAME
}
