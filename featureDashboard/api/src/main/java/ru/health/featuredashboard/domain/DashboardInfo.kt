package ru.health.featuredashboard.domain

import ru.health.featurenotifications.domain.Achievement
import kotlin.time.Duration

data class DashboardInfo(
    val hasNotifications: Boolean,
    val health: Int,
    val abstinenceDuration: Duration,
    val savedMoney: Float,
    val achievements: List<Achievement>
)
