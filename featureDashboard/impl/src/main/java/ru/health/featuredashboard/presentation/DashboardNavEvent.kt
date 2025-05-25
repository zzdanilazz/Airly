package ru.health.featuredashboard.presentation

import ru.health.featurenotifications.domain.Achievement

internal sealed interface DashboardNavEvent {

    data object OpenUploadDetail : DashboardNavEvent

    data object OpenNotifications : DashboardNavEvent

    data class OpenAchievementDetail(val achievement: Achievement) : DashboardNavEvent
}