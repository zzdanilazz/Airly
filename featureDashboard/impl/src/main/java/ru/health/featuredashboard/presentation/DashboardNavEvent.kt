package ru.health.featuredashboard.presentation

internal sealed interface DashboardNavEvent {

    data object OpenUploadDetail : DashboardNavEvent

    data object OpenNotifications : DashboardNavEvent
}