package ru.health.featuredashboard.api.domain.model

import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

data class DashboardInfo(
    val hasNotifications: Boolean,
    val health: Flow<Int>,
    val abstinenceDuration: Flow<Duration>,
    val savedMoneyFlow: Flow<Double>
)
