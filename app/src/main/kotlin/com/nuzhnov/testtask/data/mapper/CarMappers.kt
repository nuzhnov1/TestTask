package com.nuzhnov.testtask.data.mapper

import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.domen.model.Car


internal fun CarEntity.toModel() = Car(
    number = number,
    model = model,
    releaseYear = releaseYear,
    millage = millage
)
