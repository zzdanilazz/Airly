package ru.health.core.api.data

import kotlinx.coroutines.flow.Flow

interface MetaDataStore {

    suspend fun saveAchievementValue(id: Int, value: Int)

    suspend fun getAchievementValue(id: Int): Flow<Int>

    suspend fun clear()
}