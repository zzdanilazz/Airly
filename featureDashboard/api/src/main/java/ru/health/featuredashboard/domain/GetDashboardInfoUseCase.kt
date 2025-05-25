package ru.health.featuredashboard.domain

import ru.health.core.domain.result.ResultError
import ru.health.core.domain.result.RootResult

interface GetDashboardInfoUseCase {

    suspend operator fun invoke(): RootResult<DashboardInfo, ResultError>

}
