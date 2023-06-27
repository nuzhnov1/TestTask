package com.nuzhnov.testtask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nuzhnov.testtask.data.database.dao.CarDao
import com.nuzhnov.testtask.data.database.entity.CarEntity

@Database(entities = [CarEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getCarDao(): CarDao


    companion object {
        const val NAME = "local.db"
    }
}
