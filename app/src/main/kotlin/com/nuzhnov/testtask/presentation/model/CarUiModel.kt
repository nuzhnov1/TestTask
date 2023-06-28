package com.nuzhnov.testtask.presentation.model

data class CarUiModel(
    val registrationNumber: String,
    val regionCode: Int,
    val countryShort: String,
    val model: String,
    val releaseYear: Int,
    val millage: Int
)
