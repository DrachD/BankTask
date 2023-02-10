package com.testing.data

import androidx.lifecycle.LiveData
import com.testing.common.dbentities.SearchHistoryData
import com.testing.domain.repository.DatabaseBankRepository
import javax.inject.Inject

class DatabaseBankRepositoryImpl @Inject constructor(
    private val dao: BankDao
) : DatabaseBankRepository {

    override suspend fun addSearchHistory(searchHistoryData: SearchHistoryData) {
        dao.addSearchHistory(searchHistoryData)
    }

    override fun fetchAllSearchHistory(): LiveData<List<SearchHistoryData>> {
        return dao.fetchAllSearchHistory()
    }
}