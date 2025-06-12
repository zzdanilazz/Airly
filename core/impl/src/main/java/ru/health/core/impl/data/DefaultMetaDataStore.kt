package ru.health.core.impl.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.health.core.api.data.MetaDataStore
import javax.inject.Inject

class DefaultMetaDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : MetaDataStore {

    override suspend fun saveAchievementValue(id: Int, value: Int) {
        dataStore.edit {
            it[getIdPreferenceKey(id)] = value
        }
    }

    override suspend fun getAchievementValue(id: Int): Flow<Int> =
        dataStore.data.map { it[getIdPreferenceKey(id)] ?: 0 }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    private fun getIdPreferenceKey(id: Int):  Preferences.Key<Int> =
        intPreferencesKey(id.toString())
}