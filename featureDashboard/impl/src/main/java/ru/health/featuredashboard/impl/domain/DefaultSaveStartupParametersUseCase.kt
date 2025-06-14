package ru.health.featuredashboard.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.data.MetaDataStore
import ru.health.core.api.domain.result.RootResult
import ru.health.featuredashboard.api.domain.DashboardRepository
import ru.health.featuredashboard.api.domain.model.StartupParameters
import ru.health.featuredashboard.api.domain.usecase.SaveStartupParametersUseCase
import javax.inject.Inject

class DefaultSaveStartupParametersUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository,
    private val metaDataStore: MetaDataStore,
) : SaveStartupParametersUseCase {

    override suspend fun invoke(startupParameters: StartupParameters): RootResult<Unit, ResultError> = try {
        dashboardRepository.saveInterests(startupParameters.interests)
        metaDataStore.saveIsStartupParametersSaved()
        RootResult.Success(Unit)
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}
