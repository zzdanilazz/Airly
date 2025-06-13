package ru.health.featuredashboard.impl.presentation.dashboard

internal sealed interface DashboardNavEvent {

    data object OpenUploadDetail : DashboardNavEvent

    data object OpenNotifications : DashboardNavEvent
}