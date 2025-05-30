package ru.health.featurenotifications.impl.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import ru.health.featurenotifications.api.data.NotificationDataStore

class DefaultNotificationDataStore(
    private val dataStore: DataStore<Preferences>,
) : NotificationDataStore {

    override suspend fun saveToken(token: String) {
        dataStore.edit { data ->
            data[FIREBASE_TOKEN] = token
        }
    }

    override suspend fun token(): String = dataStore.data.first()[FIREBASE_TOKEN] ?: ""

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    private companion object {
        val FIREBASE_TOKEN = stringPreferencesKey("FIREBASE_TOKEN")
    }
}
