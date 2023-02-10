package com.testing.banktask.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.testing.common.GetBankCustomerEntity
import com.testing.common.dbentities.SearchHistoryData
import com.testing.domain.GeneralResponse
import com.testing.domain.repository.DatabaseBankRepository
import com.testing.domain.repository.RetrofitBankRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val retrofitBankRepository: RetrofitBankRepository,
    private val databaseBankRepository: DatabaseBankRepository
) : ViewModel() {

    private val _getBankCustomerEvent = MutableLiveData<GetBankCustomerEntity?>()
    val getBankCustomerEntity: MutableLiveData<GetBankCustomerEntity?> = _getBankCustomerEvent

    private val _showMessageEvent = MutableLiveData<String?>()
    val showMessageEvent: MutableLiveData<String?> = _showMessageEvent

    fun fetchBankCustomer(value: String) = viewModelScope.launch {

        when (val response = retrofitBankRepository.fetchBankCustomer(value)) {
            is GeneralResponse.Success -> {
                _getBankCustomerEvent.value = response.data
            }
            is GeneralResponse.Error -> {
                showMessage(response.errorMessage)
            }
        }
    }

    fun addSearchHistory(searchHistoryData: SearchHistoryData) = viewModelScope.launch {
        databaseBankRepository.addSearchHistory(searchHistoryData)
    }

    fun fetchAllSearchHistory() = databaseBankRepository.fetchAllSearchHistory()

    private fun showMessage(message: String?) {
        _showMessageEvent.value = message
    }
}