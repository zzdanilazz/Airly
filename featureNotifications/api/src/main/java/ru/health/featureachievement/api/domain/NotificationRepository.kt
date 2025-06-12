package ru.health.featurenotifications.api.domain

interface NotificationRepository {

    suspend fun saveToken(token: String)

    suspend fun token(): String

    suspend fun getNotifications(limit: Int, offset: Int): List<PushEntity>

}
