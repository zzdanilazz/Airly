package ru.health.featuredashboard.api.data

import java.util.Date

interface DashboardLocalDataSource {

    suspend fun getLastRelapsedDate(): Date

    suspend fun saveInterests(interests: Set<String>)

    suspend fun getInterests(): Set<String>
}
