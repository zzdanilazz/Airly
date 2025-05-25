package ru.health.featuredashboard.presentation

internal sealed interface DashboardAction {

    data object Init : DashboardAction

    data object OnUploadClick : DashboardAction

    data object OnNotificationsClick : DashboardAction

    data object OnAddActionClick : DashboardAction
}