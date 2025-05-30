package ru.health.featuredashboard.impl.presentation

internal sealed interface DashboardNavEvent {

    data object OpenUploadDetail : DashboardNavEvent

    data object OpenNotifications : DashboardNavEvent
}