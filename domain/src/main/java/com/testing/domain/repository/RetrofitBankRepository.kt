package com.testing.domain.repository

import com.testing.common.GetBankCustomerEntity
import com.testing.domain.GeneralResponse
import java.io.IOException

interface RetrofitBankRepository {

    /**
     * Execute request.
     * @throws HttpException
     * @throws NoInternetException
     * @throws IOException
     * @throws Exception
     */
    suspend fun fetchBankCustomer(value: String): GeneralResponse<GetBankCustomerEntity>
}