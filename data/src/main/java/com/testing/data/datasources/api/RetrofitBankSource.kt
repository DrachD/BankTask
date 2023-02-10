package com.testing.data.datasources.api

import com.testing.common.GetBankCustomerEntity
import com.testing.domain.GeneralResponse

interface RetrofitBankSource {

    suspend fun fetchBankCustomer(value: String): GeneralResponse<GetBankCustomerEntity>
}