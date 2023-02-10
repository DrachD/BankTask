package com.testing.data.datasources.api

import com.testing.data.BankApi
import com.testing.data.datasources.api.base.BaseRetrofitSource
import javax.inject.Inject

class RetrofitBankSourceImpl @Inject constructor(
    private val bankApi: BankApi
) : BaseRetrofitSource(), RetrofitBankSource {

    override suspend fun fetchBankCustomer(value: String) = wrapRetrofitException {
        bankApi.fetchBankCustomer(value)
    }
}