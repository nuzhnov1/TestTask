package com.nuzhnov.testtask.data.datasource

import com.nuzhnov.testtask.data.database.dao.CarDao
import com.nuzhnov.testtask.domen.model.CarSortType
import com.nuzhnov.testtask.domen.model.SortOrder
import javax.inject.Inject

internal class CarsLocalDataSource @Inject constructor(
    private val carDao: CarDao
) {

    fun getCarEntitiesFlow(
        number: String,
        sortType: CarSortType,
        sortOrder: SortOrder
    ) = carDao.getCarEntitiesFlow(number, sortType, sortOrder)
}
