package com.testing.data.di.modules

import com.testing.data.RetrofitBankRepositoryImpl
import com.testing.domain.repository.RetrofitBankRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RetrofitBankRepositoryModule {

    @Binds
    abstract fun provideRetrofitBankRepository(retrofitBankRepository: RetrofitBankRepositoryImpl): RetrofitBankRepository
}