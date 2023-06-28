package com.nuzhnov.testtask.presentation.mapper

import com.nuzhnov.testtask.domen.model.Car
import com.nuzhnov.testtask.presentation.model.CarUiModel

internal fun Car.toUiModel(): CarUiModel {
    val (registrationNumber, regionCode, countryShort) = number.split('-')

    return CarUiModel(
        registrationNumber = registrationNumber,
        regionCode = regionCode.toInt(),
        countryShort = countryShort,
        model = model,
        releaseYear = releaseYear,
        millage = millage
    )
}
