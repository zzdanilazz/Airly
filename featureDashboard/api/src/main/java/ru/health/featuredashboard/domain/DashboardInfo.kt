package ru.health.featuredashboard.domain

data class DashboardInfo(
    val hasNotifications: Boolean,
    val health: Int,
    val startAbstinenceTimeMillis: Long,
    val savedMoney: Float
)
