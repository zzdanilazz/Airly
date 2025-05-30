package ru.health.featuredashboard.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.first

class DefaultDashboardDataStore(
    private val dataStore: DataStore<Preferences>,
) : DashboardDataStore {

    override suspend fun saveInterests(interests: Set<String>) {
        dataStore.edit { it[INTERESTS] = interests }
    }

    override suspend fun getInterests(): Set<String> =
        dataStore.data.first()[INTERESTS] ?: emptySet()

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    private companion object {
        val INTERESTS = stringSetPreferencesKey("INTERESTS")
    }
}
