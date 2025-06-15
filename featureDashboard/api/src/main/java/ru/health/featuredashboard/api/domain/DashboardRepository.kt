package ru.health.featuredashboard.api.domain

import java.util.Date

interface DashboardRepository {

    suspend fun getLastConsumptionDate(): Date

    suspend fun saveInterests(interests: Set<String>)

    suspend fun getInterests(): Set<String>

}
