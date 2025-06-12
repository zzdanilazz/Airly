package ru.health.featuredashboard.impl.presentation.root

internal sealed interface DashboardNavEvent {

    data object OpenUploadDetail : DashboardNavEvent

    data object OpenNotifications : DashboardNavEvent
}