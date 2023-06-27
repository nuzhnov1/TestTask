package com.nuzhnov.testtask.data.mapper

import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.domen.models.Car

internal fun CarEntity.toCar() = Car(
    number = number,
    model = model,
    releaseYear = releaseYear,
    millage = millage
)
