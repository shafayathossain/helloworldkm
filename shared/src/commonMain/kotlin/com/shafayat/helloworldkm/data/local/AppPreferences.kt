package com.shafayat.helloworldkm.data.local

import com.shafayat.helloworldkm.data.Preferences
import kotlin.jvm.JvmName


class AppPreferences private constructor() {
    companion object {
        const val MESSAGE = "MESSAGE"

        @set:JvmName("appPreferencesDatabaseDriverFactory")
        private lateinit var databaseDriverFactory: DatabaseDriverFactory

        val instance: AppPreferences by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            AppPreferences(databaseDriverFactory)
        }

        fun setup(databaseDriverFactory: DatabaseDriverFactory) {
            this.databaseDriverFactory = databaseDriverFactory
            instance
        }
    }

    private constructor(databaseDriverFactory: DatabaseDriverFactory) : this() {
        database = Database(databaseDriverFactory)
        preferences = database.getPreferences()
    }

    private lateinit var database: Database
    private lateinit var preferences: List<Preferences>

    var message: String
        get() {
            return preferences.firstOrNull { it.key == MESSAGE }?.value_ ?: ""
        }
        set(value) {
            database.insertIntoPreference(MESSAGE, value.lowercase())
        }
}