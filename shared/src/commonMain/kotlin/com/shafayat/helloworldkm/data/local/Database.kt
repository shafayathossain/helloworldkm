package com.shafayat.helloworldkm.data.local

import com.shafayat.hellowordlkm.AppDatabase
import com.shafayat.helloworldkm.data.Preferences


internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.preferencesQueries

    internal fun getPreferences(): List<Preferences> {
        return dbQuery.getPreferences().executeAsList()
    }
    internal fun insertIntoPreference(key: String, value: String) {
        return dbQuery.insertIntoPreference(key, value)
    }

    internal fun removeFromPreference(key: String) {
        return dbQuery.deleteAKeyFromPreference(key)
    }
}