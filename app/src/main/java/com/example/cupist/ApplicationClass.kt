package com.example.cupist

import android.app.Application
import android.content.Context

class ApplicationClass : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        context = base
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        lateinit var context: Context
    }
}