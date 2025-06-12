package ru.health.featuredashboard.impl.presentation.root

import androidx.compose.runtime.Immutable
import kotlin.time.Duration

@Immutable
internal data class DashboardUiState(
    val hasNotifications: Boolean = false,
    val health: Int = 100,
    val abstinenceDuration: Duration = Duration.ZERO,
    val savedMoney: Float = 0f
)

//internal fun DashboardUiState.copy(info: DashboardInfo) = DashboardUiState(
//    hasNotifications = info.hasNotifications,
//    health = info.health,
//    lastRelapsedDate = info.abstinenceDuration.time,
//    savedMoney = info.savedMoney
//)

