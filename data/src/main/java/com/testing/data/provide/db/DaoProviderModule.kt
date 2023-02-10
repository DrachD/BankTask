package com.testing.data.provide.db

import android.content.Context
import androidx.room.Room
import com.testing.data.BankDao
import com.testing.data.datasources.db.BankDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DaoProviderModule {

    @Singleton
    @Provides
    fun bankDatabase(context: Context): BankDatabase {
        return Room.databaseBuilder(
            context,
            BankDatabase::class.java,
            "bank_db"
        ).build()
    }

    @Singleton
    @Provides
    fun bankDao(database: BankDatabase): BankDao {
        return database.bankDao()
    }
}