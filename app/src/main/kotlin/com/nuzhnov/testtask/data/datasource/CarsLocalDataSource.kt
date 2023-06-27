package com.nuzhnov.testtask.data.datasource

import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.domen.models.SortType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class CarsLocalDataSource @Inject constructor(

) {

    fun getCarEntitiesFlow(sortType: SortType): Flow<List<CarEntity>> {
        TODO("Not yet implemented")
    }

    fun getCarEntitiesByNumber(number: String, sortType: SortType): Flow<List<CarEntity>> {
        TODO("Not yet implemented")
    }
}
