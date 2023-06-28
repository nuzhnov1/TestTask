package com.nuzhnov.testtask.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.data.database.entity.CarEntity.Companion.NUMBER_FIELD_NAME
import com.nuzhnov.testtask.data.database.entity.CarEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CarDao {
    @Query("SELECT * FROM $TABLE_NAME ORDER BY :sortField ASC")
    fun getSortedCarEntitiesFlow(sortField: String): Flow<List<CarEntity>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $NUMBER_FIELD_NAME = number ORDER BY :sortField ASC")
    fun getSortedCarEntitiesByNumberFlow(number: String, sortField: String): Flow<List<CarEntity>>
}
