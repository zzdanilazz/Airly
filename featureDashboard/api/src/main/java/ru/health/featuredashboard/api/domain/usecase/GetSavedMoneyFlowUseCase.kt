package ru.health.featuredashboard.api.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import kotlin.time.Duration

interface GetSavedMoneyFlowUseCase {

    suspend operator fun invoke(durationFlow: Flow<Duration>): RootResult<Flow<Double>, ResultError>

}