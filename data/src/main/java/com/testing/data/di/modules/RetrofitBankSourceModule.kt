package com.testing.data.di.modules

import com.testing.data.datasources.api.RetrofitBankSource
import com.testing.data.datasources.api.RetrofitBankSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RetrofitBankSourceModule {

    @Binds
    abstract fun provideRetrofitBankSource(retrofitBankSource: RetrofitBankSourceImpl): RetrofitBankSource
}