package com.nuzhnov.testtask.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CarEntity.TABLE_NAME)
internal data class CarEntity(
    @[PrimaryKey(autoGenerate = true) ColumnInfo(name = NUMBER_FIELD_NAME)] val number: String,
    @ColumnInfo(name = MODEL_FIELD_NAME, index = true) val model: String,
    @ColumnInfo(name = RELEASE_YEAR_FIELD_NAME, index = true) val releaseYear: Int,
    @ColumnInfo(name = MILLAGE_FIELD_NAME, index = true) val millage: Int
) {

    companion object {
        const val TABLE_NAME = "car"

        const val NUMBER_FIELD_NAME = "number"
        const val MODEL_FIELD_NAME = "model"
        const val RELEASE_YEAR_FIELD_NAME = "release_year"
        const val MILLAGE_FIELD_NAME = "millage"
    }
}
