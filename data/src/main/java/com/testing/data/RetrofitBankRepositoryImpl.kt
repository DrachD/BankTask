package com.testing.data

import com.testing.common.GetBankCustomerEntity
import com.testing.data.datasources.api.RetrofitBankSource
import com.testing.domain.GeneralResponse
import com.testing.domain.repository.RetrofitBankRepository
import javax.inject.Inject

class RetrofitBankRepositoryImpl @Inject constructor(
    private val retrofitBankSource: RetrofitBankSource
) : RetrofitBankRepository {

    override suspend fun fetchBankCustomer(value: String): GeneralResponse<GetBankCustomerEntity> {
        return retrofitBankSource.fetchBankCustomer(value)
    }
}