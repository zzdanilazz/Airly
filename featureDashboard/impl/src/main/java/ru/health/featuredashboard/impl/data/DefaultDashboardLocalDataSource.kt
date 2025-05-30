package ru.health.featuredashboard.impl.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.health.featuredashboard.api.data.DashboardDataStore
import ru.health.featuredashboard.api.data.DashboardLocalDataSource
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset
import java.util.Date
import javax.inject.Inject
import kotlin.time.ExperimentalTime

internal class DefaultDashboardLocalDataSource @Inject constructor(
    private val dashboardDataStore: DashboardDataStore,
) : DashboardLocalDataSource {

    @OptIn(ExperimentalTime::class)
    override suspend fun getLastRelapsedDate(): Date = withContext(Dispatchers.IO) {
        //todo бд
        Date(LocalDateTime.of(2025, Month.MAY, 30, 12, 32).toInstant(ZoneOffset.UTC).toEpochMilli())
    }

    override suspend fun saveInterests(interests: Set<String>) = withContext(Dispatchers.IO) {
        dashboardDataStore.saveInterests(interests)
    }

    override suspend fun getInterests(): Set<String> = withContext(Dispatchers.IO) {
        dashboardDataStore.getInterests()
    }
}
