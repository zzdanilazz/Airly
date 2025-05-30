package ru.health.featuredashboard.domain

import java.util.Date

interface DashboardRepository {

    suspend fun getLastRelapsedDate(): Date

    suspend fun saveInterests(interests: Set<String>)

    suspend fun getInterests(): Set<String>

}
