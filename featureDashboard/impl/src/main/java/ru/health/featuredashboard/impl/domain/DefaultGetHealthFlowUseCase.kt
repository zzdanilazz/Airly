package ru.health.featuredashboard.impl.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featuredashboard.api.domain.usecase.GetHealthFlowUseCase
import ru.health.featureliquid.api.domain.LiquidRepository
import javax.inject.Inject
import kotlin.time.Duration

class DefaultGetHealthFlowUseCase @Inject constructor(
    private val liquidRepository: LiquidRepository
) : GetHealthFlowUseCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(
        currentAbstinenceDuration: Flow<Duration>
    ): RootResult<Flow<Int>, ResultError> = try {
        val healthFlow = currentAbstinenceDuration
            .combine(liquidRepository.getConsumptionCountFlow()) { duration, vapeCount ->
                val durationBuff = when (duration.inWholeHours) {
                    in 0 until 6  -> ONE_HOUR_ABSTINENCE_BUFF
                    in 6 until 12 -> SIX_HOUR_ABSTINENCE_BUFF
                    else -> TWELVE_HOUR_ABSTINENCE_BUFF
                }
                INITIAL_VALUE - vapeCount * ONE_TIME_VAPING_DEBUFF + durationBuff
            }
            .distinctUntilChanged()
        RootResult.Success(healthFlow)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }

    companion object {
        private const val INITIAL_VALUE = 100
        private const val ONE_TIME_VAPING_DEBUFF = 3
        private const val ONE_HOUR_ABSTINENCE_BUFF = 1
        private const val SIX_HOUR_ABSTINENCE_BUFF = 9
        private const val TWELVE_HOUR_ABSTINENCE_BUFF = 14
    }
}




