package ru.health.featurenotifications.api.data

interface NotificationDataStore {

    suspend fun saveToken(token: String)

    suspend fun token(): String

    suspend fun clear()
}
