package ru.health.featuredashboard.presentation

import androidx.compose.runtime.Immutable
import ru.health.featuredashboard.domain.DashboardInfo
import ru.health.featuredashboard.presentation.model.AbstinencePeriod
import ru.health.featuredashboard.presentation.ui.dashboardUiStatePreview

@Immutable
internal data class DashboardUiState(
    val hasNotifications: Boolean = false,
    val health: Int = 100,
    val abstinencePeriod: AbstinencePeriod = AbstinencePeriod.Zero,
    val savedMoney: Float = 0f
)

internal fun DashboardUiState.copy(info: DashboardInfo): DashboardUiState = dashboardUiStatePreview

