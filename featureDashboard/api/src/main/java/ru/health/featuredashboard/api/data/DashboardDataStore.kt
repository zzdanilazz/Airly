package ru.health.featuredashboard.api.data

interface DashboardDataStore {

    suspend fun saveInterests(interests: Set<String>)

    suspend fun getInterests(): Set<String>

    suspend fun clear()
}
