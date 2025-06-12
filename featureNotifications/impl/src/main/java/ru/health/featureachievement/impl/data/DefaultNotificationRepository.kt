package ru.health.featurenotifications.impl.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.health.core.api.data.date.DateFormatter
import ru.health.featurenotifications.api.data.NotificationDataStore
import ru.health.featurenotifications.api.domain.NotificationRepository
import ru.health.featurenotifications.api.domain.PushEntity
import javax.inject.Inject

internal class DefaultNotificationRepository @Inject constructor(
    private val notificationDataStore: NotificationDataStore,
    private val dateFormatter: DateFormatter
) : NotificationRepository {

    override suspend fun saveToken(token: String) = withContext(Dispatchers.IO) {
        notificationDataStore.saveToken(token)
    }

    override suspend fun token(): String  = withContext(Dispatchers.IO) {
        notificationDataStore.token()
    }

    override suspend fun getNotifications(
        limit: Int,
        offset: Int
    ): List<PushEntity> = withContext(Dispatchers.IO) {
        emptyList()
//        val notificationDataList = notificationRemoteDataSource
//            .getNotifications(limit, offset)
//            .getOrThrow()
//
//        notificationDataList.map { it.toDomain(dateFormatter) }
    }
}