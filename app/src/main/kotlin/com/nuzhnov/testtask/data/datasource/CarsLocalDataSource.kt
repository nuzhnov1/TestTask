package com.nuzhnov.testtask.data.datasource

import com.nuzhnov.testtask.data.database.dao.CarDao
import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.domen.model.CarSortType
import com.nuzhnov.testtask.domen.model.SortOrder
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CarsLocalDataSource @Inject constructor(
    private val carDao: CarDao
) {

    fun getCarEntitiesFlow(
        sortType: CarSortType,
        sortOrder: SortOrder
    ) = carDao
        .getSortedCarEntitiesFlow()
        .map { entityList -> entityList.sortedBy(sortType, sortOrder) }

    fun getCarEntitiesByNumber(
        number: String,
        sortType: CarSortType,
        sortOrder: SortOrder
    ) = carDao
        .getSortedCarEntitiesByNumberFlow(number)
        .map { entityList -> entityList.sortedBy(sortType, sortOrder) }

    private fun List<CarEntity>.sortedBy(
        sortType: CarSortType,
        sortOrder: SortOrder
    ): List<CarEntity> {
        val ascSortedEntityList = when (sortType) {
            CarSortType.NUMBER       -> sortedBy { it.number }
            CarSortType.MODEL        -> sortedBy { it.model }
            CarSortType.RELEASE_YEAR -> sortedBy { it.releaseYear }
            CarSortType.MILLAGE      -> sortedBy { it.millage }
        }

        return if (sortOrder == SortOrder.DESC) {
            ascSortedEntityList.reversed()
        } else {
            ascSortedEntityList
        }
    }
}
