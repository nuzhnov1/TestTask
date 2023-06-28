package com.nuzhnov.testtask.data.mapper

import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.domen.model.Car
import com.nuzhnov.testtask.domen.model.CarSortType


internal fun CarEntity.toModel() = Car(
    number = number,
    model = model,
    releaseYear = releaseYear,
    millage = millage
)

internal fun CarSortType.toEntityFieldName() = when (this) {
    CarSortType.NUMBER       -> CarEntity.NUMBER_FIELD_NAME
    CarSortType.MODEL        -> CarEntity.MODEL_FIELD_NAME
    CarSortType.RELEASE_YEAR -> CarEntity.RELEASE_YEAR_FIELD_NAME
    CarSortType.MILLAGE      -> CarEntity.MILLAGE_FIELD_NAME
}
