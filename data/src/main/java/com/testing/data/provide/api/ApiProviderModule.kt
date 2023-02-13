package com.testing.data.provide.api

import android.content.Context
import com.testing.data.BankApi
import com.testing.data.Constants
import com.testing.data.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiProviderModule {

    /**
     * Building an OkHttpClient instance with an internet interceptor.
     */
    @Singleton
    @Provides
    fun createOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .build()
    }

    /**
     * Create an instance of Retrofit client.
     */
    @Singleton
    @Provides
    fun createRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .client(createOkHttpClient(context))
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provide the Bank's api.
     */
    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit): BankApi {
        return retrofit.create(BankApi::class.java)
    }
}