package ru.health.featuredashboard.impl.domain

import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featuredashboard.api.domain.DashboardRepository
import ru.health.featuredashboard.api.domain.usecase.GetInterestsUseCase
import javax.inject.Inject

class DefaultGetInterestsUseCase @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : GetInterestsUseCase {

    override suspend fun invoke(): RootResult<List<String>, ResultError> = try {
        RootResult.Success(dashboardRepository.getInterests().toList())
    } catch (_: Exception) {
        RootResult.Failure(RequestError.GENERIC)
    }
}




