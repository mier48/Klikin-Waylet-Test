package com.albertomier.klikinwaylettest

import android.app.Application
import android.content.Context
import com.albertomier.klikinwaylettest.core.Preference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KlikinWayletTestApp : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var preference: Preference
        var instance: KlikinWayletTestApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        preference = Preference(applicationContext)
        val context: Context = applicationContext()
    }
}