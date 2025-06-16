package ru.health.core.api.data

interface MetaDataStore {

    suspend fun saveIsStartupParametersSaved()

    suspend fun getIsStartupParametersSaved(): Boolean

    suspend fun saveIsProgressShared()

    suspend fun getIsProgressShared(): Boolean

    suspend fun clear()
}