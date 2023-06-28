package com.nuzhnov.testtask.data.database

import com.nuzhnov.testtask.data.database.dao.CarDao
import com.nuzhnov.testtask.data.database.entity.CarEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CarEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getCarDao(): CarDao


    companion object {
        const val NAME = "local.db"
    }
}
