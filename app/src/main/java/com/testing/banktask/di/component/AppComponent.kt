package com.testing.banktask.di.component

import android.content.Context
import com.testing.banktask.presenter.MainFragment
import com.testing.data.di.modules.DatabaseBankRepositoryModule
import com.testing.data.di.modules.RetrofitBankRepositoryModule
import com.testing.data.di.modules.RetrofitBankSourceModule
import com.testing.data.provide.api.ApiProviderModule
import com.testing.data.provide.db.DaoProviderModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiProviderModule::class,
    DaoProviderModule::class,
    RetrofitBankSourceModule::class,
    RetrofitBankRepositoryModule::class,
    DatabaseBankRepositoryModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: MainFragment)
}