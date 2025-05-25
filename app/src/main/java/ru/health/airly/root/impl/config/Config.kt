package ru.health.airly.root.impl.config

import kotlinx.serialization.Serializable
import ru.health.featurenotifications.domain.Achievement

@Serializable
sealed interface Config {

    @Serializable
    data object Tab : Config

    @Serializable
    data class AchievementDetail(val achievement: Achievement) : Config

    @Serializable
    data object NotificationList : Config

    @Serializable
    data object UploadDetail : Config
}