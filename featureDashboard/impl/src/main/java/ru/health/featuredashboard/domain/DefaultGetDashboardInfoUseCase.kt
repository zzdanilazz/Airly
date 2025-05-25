package ru.health.featuredashboard.domain

import ru.health.core.domain.result.RequestError
import ru.health.core.domain.result.ResultError
import ru.health.core.domain.result.RootResult
import javax.inject.Inject
import kotlin.time.Duration

class DefaultGetDashboardInfoUseCase @Inject constructor(

) : GetDashboardInfoUseCase {

    override suspend fun invoke(): RootResult<DashboardInfo, ResultError> = try {
        RootResult.Success(
            DashboardInfo(
                hasNotifications = true,
                health = 86,
                abstinenceDuration = Duration.ZERO,
                savedMoney = 3_398.08f,
                achievements = emptyList()
            )
        )
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
