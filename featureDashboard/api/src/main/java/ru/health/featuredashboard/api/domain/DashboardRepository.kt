package ru.health.featuredashboard.api.domain

import java.util.Date

interface DashboardRepository {

    suspend fun getLastRelapsedDate(): Date

    suspend fun saveInterests(interests: Set<String>)

    suspend fun getInterests(): Set<String>

}
