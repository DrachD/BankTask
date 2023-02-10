package com.testing.data.di.modules

import com.testing.data.DatabaseBankRepositoryImpl
import com.testing.domain.repository.DatabaseBankRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DatabaseBankRepositoryModule {

    @Binds
    abstract fun provideDatabaseBankRepository(databaseBankRepository: DatabaseBankRepositoryImpl): DatabaseBankRepository
}