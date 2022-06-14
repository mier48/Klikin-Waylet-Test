package com.albertomier.klikinwaylettest.core

import android.content.Context

class Preference(context: Context) {

    private val storage = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)

    fun setString(name: String, value: String) {
        storage.edit().putString(name, value).apply()
    }

    fun getString(name: String): String {
        return storage.getString(name, "")!!
    }

    fun setLong(name: String, value: Long) {
        storage.edit().putLong(name, value).apply()
    }

    fun getLong(name: String): Long {
        return storage.getLong(name, -99)
    }

    fun setInt(name: String, value: Int) {
        storage.edit().putInt(name, value).apply()
    }

    fun getInt(name: String): Int {
        return storage.getInt(name, 0)
    }

    fun clear() {
        return storage.edit().clear().apply()
    }
}