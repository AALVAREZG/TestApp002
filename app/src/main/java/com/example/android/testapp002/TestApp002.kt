package com.example.android.testapp002

import android.app.Application
import timber.log.Timber

class TestApp002 : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("LOG - onCreate (app)")
    }

}