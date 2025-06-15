package ru.health.featuredashboard.impl.domain

import kotlinx.coroutines.async
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.supervisorScope
import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.core.impl.domain.getDurationFlow
import ru.health.featuredashboard.api.domain.DashboardRepository
import ru.health.featuredashboard.api.domain.model.DashboardInfo
import ru.health.featuredashboard.api.domain.usecase.GetDashboardInfoUseCase
import ru.health.featureliquid.api.domain.LiquidRepository
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class DefaultGetDashboardInfoUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository,
    private val liquidRepository: LiquidRepository,
    private val getSavedMoneyFlowUseCase: DefaultGetSavedMoneyFlowUseCase,
) : GetDashboardInfoUseCase {

    @OptIn(ExperimentalTime::class)
    override suspend fun invoke(): RootResult<DashboardInfo, ResultError> = supervisorScope {
        val currentAbstinenceDurationDeferred = async {
            val deviceId = liquidRepository.getLatestDevice()?.id
            deviceId?.let {
                val lastDate = liquidRepository.getLastConsumptionDate(it)
                getDurationFlow(lastDate)
            } ?: emptyFlow()
        }

        val totalAbstinenceDurationDeferred = async {
            val deviceId = liquidRepository.getLatestDevice()?.id
            deviceId?.let {
                val firstDate = liquidRepository.getFirstConsumptionDate(it)
                getDurationFlow(firstDate)
            } ?: emptyFlow()
        }

        try {
            val currentAbstinenceDuration = currentAbstinenceDurationDeferred.await()
            val totalAbstinenceDuration = totalAbstinenceDurationDeferred.await()
            RootResult.Success(
                DashboardInfo(
                    hasNotifications = true,
                    health = 86,
                    abstinenceDuration = currentAbstinenceDuration,
                    savedMoneyFlow = getSavedMoneyFlowUseCase(totalAbstinenceDuration)
                        .dataOrDefault(emptyFlow())
                )
            )
        } catch (_: Exception) {
            RootResult.Failure(RequestError.GENERIC)
        }
    }
}
