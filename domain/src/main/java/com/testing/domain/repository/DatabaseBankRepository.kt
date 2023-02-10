package com.testing.domain.repository

import androidx.lifecycle.LiveData
import com.testing.common.dbentities.SearchHistoryData

interface DatabaseBankRepository {

    suspend fun addSearchHistory(searchHistoryData: SearchHistoryData)
    fun fetchAllSearchHistory(): LiveData<List<SearchHistoryData>>
}