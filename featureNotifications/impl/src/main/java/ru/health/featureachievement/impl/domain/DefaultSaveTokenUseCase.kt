package ru.health.featurenotifications.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featurenotifications.api.domain.NotificationRepository
import ru.health.featurenotifications.api.domain.SaveTokenUseCase
import javax.inject.Inject

class DefaultSaveTokenUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) : SaveTokenUseCase {
    override suspend fun invoke(token: String): RootResult<Unit, ResultError> {
        return try {
            RootResult.Success(notificationRepository.saveToken(token))
        } catch (_: Throwable) {
            RootResult.Failure(RequestError.GENERIC)
        }
    }
}
