package ru.health.featuredashboard.domain

import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult

interface GetDashboardInfoUseCase {

    suspend operator fun invoke(): RootResult<DashboardInfo, ResultError>

}
