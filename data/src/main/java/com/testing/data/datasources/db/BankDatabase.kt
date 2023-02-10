package com.testing.data.datasources.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.testing.common.dbentities.SearchHistoryData
import com.testing.data.BankDao

@Database(entities = [SearchHistoryData::class], version = 1)
abstract class BankDatabase : RoomDatabase() {

    abstract fun bankDao(): BankDao
}