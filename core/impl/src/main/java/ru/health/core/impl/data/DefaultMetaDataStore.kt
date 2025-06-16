package ru.health.core.impl.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import ru.health.core.api.data.MetaDataStore
import javax.inject.Inject

class DefaultMetaDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : MetaDataStore {

    override suspend fun saveIsStartupParametersSaved() {
        dataStore.edit {
            it[IS_STARTUP_PARAMETERS_SAVED] = true
        }
    }

    override suspend fun getIsStartupParametersSaved(): Boolean =
        dataStore.data.first()[IS_STARTUP_PARAMETERS_SAVED] ?: false

    override suspend fun saveIsProgressShared() {
        dataStore.edit {
            it[IS_PROGRESS_SHARED] = true
        }
    }

    override suspend fun getIsProgressShared(): Boolean =
        dataStore.data.first()[IS_PROGRESS_SHARED] ?: false

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    companion object {
        private val IS_STARTUP_PARAMETERS_SAVED = booleanPreferencesKey("IS_STARTUP_PARAMETERS_SAVED")
        private val IS_PROGRESS_SHARED = booleanPreferencesKey("IS_PROGRESS_SHARED")
    }
}