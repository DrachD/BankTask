package com.testing.data.provide.api

import com.testing.data.BankApi
import com.testing.data.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiProviderModule {

    @Singleton
    @Provides
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit): BankApi {
        return retrofit.create(BankApi::class.java)
    }
}