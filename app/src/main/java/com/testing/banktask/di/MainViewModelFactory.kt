package com.testing.banktask.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.testing.banktask.presenter.MainViewModel
import com.testing.domain.repository.DatabaseBankRepository
import com.testing.domain.repository.RetrofitBankRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val retrofitBankRepository: RetrofitBankRepository,
    private val databaseBankRepository: DatabaseBankRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(retrofitBankRepository, databaseBankRepository) as T
    }
}