package ru.health.featuredashboard.api.domain.usecase

import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featuredashboard.api.domain.DashboardInfo

interface GetDashboardInfoUseCase {

    suspend operator fun invoke(): RootResult<DashboardInfo, ResultError>

}