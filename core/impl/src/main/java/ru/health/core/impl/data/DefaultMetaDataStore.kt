package ru.health.core.impl.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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

    override suspend fun saveIsStartupParametersSaved() {
        dataStore.edit {
            it[IS_STARTUP_PARAMETERS_SAVED] = true
        }
    }

    override suspend fun getIsStartupParametersSaved(): Boolean =
        dataStore.data.first()[IS_STARTUP_PARAMETERS_SAVED] ?: false

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    private fun getIdPreferenceKey(id: Int):  Preferences.Key<Int> =
        intPreferencesKey(id.toString())

    companion object {
        private val IS_STARTUP_PARAMETERS_SAVED = booleanPreferencesKey("IS_STARTUP_PARAMETERS_SAVED")
    }
}