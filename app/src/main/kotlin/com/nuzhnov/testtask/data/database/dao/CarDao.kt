package com.nuzhnov.testtask.data.database.dao

import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.data.database.entity.CarEntity.Companion.NUMBER_FIELD_NAME
import com.nuzhnov.testtask.data.database.entity.CarEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Query

@Dao
internal interface CarDao {
    @Query("SELECT * FROM $TABLE_NAME WHERE $NUMBER_FIELD_NAME LIKE :numberPattern")
    fun getCarEntitiesFlow(numberPattern: String): Flow<List<CarEntity>>
}
