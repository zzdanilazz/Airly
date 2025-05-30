package ru.health.featuredashboard.impl.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.health.featuredashboard.api.data.DashboardLocalDataSource
import ru.health.featuredashboard.api.domain.DashboardRepository
import java.util.Date
import javax.inject.Inject

internal class DefaultDashboardRepository @Inject constructor(
    private val dashboardLocalDataSource: DashboardLocalDataSource,
) : DashboardRepository {

    override suspend fun getLastRelapsedDate(): Date =
        dashboardLocalDataSource.getLastRelapsedDate()

    override suspend fun saveInterests(interests: Set<String>) = withContext(Dispatchers.IO) {
        dashboardLocalDataSource.saveInterests(interests)
    }

    override suspend fun getInterests(): Set<String> = withContext(Dispatchers.IO) {
        dashboardLocalDataSource.getInterests()
    }
}
