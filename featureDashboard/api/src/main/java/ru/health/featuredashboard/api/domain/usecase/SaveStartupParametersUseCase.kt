package ru.health.featuredashboard.api.domain.usecase

import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featuredashboard.api.domain.model.StartupParameters

interface SaveStartupParametersUseCase {

    suspend operator fun invoke(startupParameters: StartupParameters): RootResult<Unit, ResultError>

}