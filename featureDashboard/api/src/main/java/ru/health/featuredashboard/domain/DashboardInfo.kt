package ru.health.featuredashboard.domain

import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

data class DashboardInfo(
    val hasNotifications: Boolean,
    val health: Int,
    val abstinenceDuration: Flow<Duration>,
    val savedMoney: Float
)
