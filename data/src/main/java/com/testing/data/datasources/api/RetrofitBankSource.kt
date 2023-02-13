package com.testing.data.datasources.api

import com.testing.common.GetBankCustomerEntity
import com.testing.domain.GeneralResponse
import java.io.IOException

interface RetrofitBankSource {

    /**
     * Execute request.
     * @throws HttpException
     * @throws NoInternetException
     * @throws IOException
     * @throws Exception
     */
    suspend fun fetchBankCustomer(value: String): GeneralResponse<GetBankCustomerEntity>
}