package com.nuzhnov.testtask.data.datasource

import com.nuzhnov.testtask.data.database.dao.CarDao
import javax.inject.Inject

internal class CarsLocalDataSource @Inject constructor(
    private val carDao: CarDao
) {

    fun getCarEntitiesFlow(sortField: String) =
        carDao.getSortedCarEntitiesFlow(sortField)

    fun getCarEntitiesByNumber(number: String, sortField: String) =
        carDao.getSortedCarEntitiesByNumberFlow(number, sortField)
}
