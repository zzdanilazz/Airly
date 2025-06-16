package ru.health.featuredashboard.impl.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.supervisorScope
import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.core.impl.domain.getDurationFlow
import ru.health.featuredashboard.api.domain.model.DashboardInfo
import ru.health.featuredashboard.api.domain.usecase.GetDashboardInfoUseCase
import ru.health.featuredashboard.api.domain.usecase.GetHealthFlowUseCase
import ru.health.featureliquid.api.domain.LiquidRepository
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class DefaultGetDashboardInfoUseCase @Inject constructor(
    private val liquidRepository: LiquidRepository,
    private val getSavedMoneyFlowUseCase: DefaultGetSavedMoneyFlowUseCase,
    private val getHealthFlowUseCase: GetHealthFlowUseCase
) : GetDashboardInfoUseCase {

    @OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)
    override suspend fun invoke(): RootResult<DashboardInfo, ResultError> = supervisorScope {
        val currentAbstinenceDurationDeferred = async {
            liquidRepository.getLatestDeviceFlow()
                .mapLatest { device ->
                    device?.id
                }
                .flatMapLatest { deviceId ->
                    if (deviceId != null) {
                        liquidRepository.getLatestConsumptionDateFlow(deviceId)
                            .flatMapLatest { date ->
                                date?.let { getDurationFlow(it) } ?: emptyFlow()
                            }
                    } else {
                        emptyFlow()
                    }
                }
        }

        val totalAbstinenceDurationDeferred = async {
            val deviceId = liquidRepository.getEarliestDevice()?.id
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
                    health = getHealthFlowUseCase(currentAbstinenceDuration)
                        .dataOrDefault(emptyFlow()),
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
