package ru.health.airly.root.impl.config

import kotlinx.serialization.Serializable

@Serializable
sealed interface Config {

    @Serializable
    data object Tab : Config

    @Serializable
    data object NotificationList : Config

    @Serializable
    data object UploadDetail : Config

    @Serializable
    data object StartupParameters : Config
}