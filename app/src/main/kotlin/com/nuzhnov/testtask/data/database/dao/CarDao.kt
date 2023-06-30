package com.nuzhnov.testtask.data.database.dao

import com.nuzhnov.testtask.data.database.entity.CarEntity
import com.nuzhnov.testtask.data.database.entity.CarEntity.Companion.NUMBER_FIELD_NAME
import com.nuzhnov.testtask.data.database.entity.CarEntity.Companion.TABLE_NAME
import com.nuzhnov.testtask.data.mapper.toQueryString
import com.nuzhnov.testtask.domen.model.CarSortType
import com.nuzhnov.testtask.domen.model.SortOrder
import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
internal abstract class CarDao {
    fun getCarEntitiesFlow(
        number: String,
        sortType: CarSortType,
        sortOrder: SortOrder
    ): Flow<List<CarEntity>> {
        val numberPattern = "%$number%"
        val sortTypeString = sortType.toQueryString()
        val sortOrderString = sortOrder.toQueryString()

        val query = SimpleSQLiteQuery(
            """
            SELECT * FROM $TABLE_NAME WHERE $NUMBER_FIELD_NAME LIKE '$numberPattern'
            ORDER BY $sortTypeString $sortOrderString
            """.trimIndent().replace(oldChar = '\n', newChar = ' ')
        )

        return getCarEntitiesFlow(query)
    }

    @RawQuery(observedEntities = [CarEntity::class])
    protected abstract fun getCarEntitiesFlow(query: SupportSQLiteQuery): Flow<List<CarEntity>>
}
