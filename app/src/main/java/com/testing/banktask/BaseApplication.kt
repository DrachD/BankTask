package com.testing.banktask

import android.app.Application
import com.testing.banktask.di.component.AppComponent
import com.testing.banktask.di.component.DaggerAppComponent

class BaseApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}