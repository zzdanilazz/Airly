package ru.health.featuredashboard.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featuredashboard.api.domain.DashboardRepository
import ru.health.featuredashboard.api.domain.StartupParameters
import ru.health.featuredashboard.api.domain.usecase.SaveStartupParametersUseCase
import javax.inject.Inject

class DefaultSaveStartupParametersUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : SaveStartupParametersUseCase {

    override suspend fun invoke(startupParameters: StartupParameters): RootResult<Unit, ResultError> = try {
        dashboardRepository.saveInterests(startupParameters.interests)
        RootResult.Success(Unit)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
