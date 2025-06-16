package ru.health.featuredashboard.api.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import kotlin.time.Duration

interface GetHealthFlowUseCase {

    suspend operator fun invoke(currentAbstinenceDuration: Flow<Duration>): RootResult<Flow<Int>, ResultError>

}