package com.testing.data

import com.testing.common.GetBankCustomerEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface BankApi {

    @GET("{value}")
    suspend fun fetchBankCustomer(@Path("value") value: String): Response<GetBankCustomerEntity>
}