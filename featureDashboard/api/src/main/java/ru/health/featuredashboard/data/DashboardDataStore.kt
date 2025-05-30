package ru.health.featuredashboard.data

interface DashboardDataStore {

    suspend fun saveInterests(interests: Set<String>)

    suspend fun getInterests(): Set<String>

    suspend fun clear()
}
