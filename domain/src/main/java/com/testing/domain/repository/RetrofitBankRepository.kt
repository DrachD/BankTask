package com.testing.domain.repository

import com.testing.common.GetBankCustomerEntity
import com.testing.domain.GeneralResponse

interface RetrofitBankRepository {

    suspend fun fetchBankCustomer(value: String): GeneralResponse<GetBankCustomerEntity>
}