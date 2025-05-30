package ru.health.featuredashboard.impl.domain

import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.supervisorScope
import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featuredashboard.api.domain.DashboardInfo
import ru.health.featuredashboard.api.domain.DashboardRepository
import ru.health.featuredashboard.api.domain.GetDashboardInfoUseCase
import java.time.Instant
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime

class DefaultGetDashboardInfoUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : GetDashboardInfoUseCase {

    @OptIn(ExperimentalTime::class)
    override suspend fun invoke(): RootResult<DashboardInfo, ResultError> = supervisorScope {
        val abstinenceDurationDeferred = async {
            flow {
                while (currentCoroutineContext().isActive) {
                    val currentTimeMillis = Instant.now().toEpochMilli()
                    val lastRelapsedTimeMillis = dashboardRepository.getLastRelapsedDate().time

                    emit((currentTimeMillis - lastRelapsedTimeMillis).milliseconds)
                    delay(1_000)
                }
            }
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
