package ru.health.featuredashboard.presentation

import ru.health.featurenotifications.domain.Achievement

internal sealed interface DashboardAction {

    data object Init : DashboardAction

    data object OnUploadClick : DashboardAction

    data object OnNotificationsClick : DashboardAction

    data class OnAchievementClick(val achievement: Achievement) : DashboardAction

    data object OnAddActionClick : DashboardAction
}