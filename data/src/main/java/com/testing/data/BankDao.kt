package com.testing.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testing.common.dbentities.SearchHistoryData

@Dao
interface BankDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSearchHistory(searchHistoryData: SearchHistoryData)

    @Query("SELECT * FROM searchHistory ORDER BY id DESC")
    fun fetchAllSearchHistory(): LiveData<List<SearchHistoryData>>
}