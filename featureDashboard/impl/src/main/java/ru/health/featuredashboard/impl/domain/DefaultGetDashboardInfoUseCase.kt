package ru.health.featuredashboard.impl.domain

import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.core.impl.domain.getDurationFlow
import ru.health.featuredashboard.api.domain.model.DashboardInfo
import ru.health.featuredashboard.api.domain.DashboardRepository
import ru.health.featuredashboard.api.domain.usecase.GetDashboardInfoUseCase
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class DefaultGetDashboardInfoUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : GetDashboardInfoUseCase {

    @OptIn(ExperimentalTime::class)
    override suspend fun invoke(): RootResult<DashboardInfo, ResultError> = supervisorScope {
        val abstinenceDurationDeferred = async {
            getDurationFlow(dashboardRepository.getLastRelapsedDate())
        }

        try {
            val abstinenceDuration = abstinenceDurationDeferred.await()
            RootResult.Success(
                DashboardInfo(
                    hasNotifications = true,
                    health = 86,
                    abstinenceDuration = abstinenceDuration,
                    savedMoney = 3_398.08f
                )
            )
        } catch (_: Exception) {
            RootResult.Failure(RequestError.GENERIC)
        }
    }
}
