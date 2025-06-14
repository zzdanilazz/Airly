package ru.health.airly.root.impl.config

import kotlinx.serialization.Serializable
import ru.health.core.api.domain.FlaconParams

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

    @Serializable
    data class InputLiquid(val flaconParams: FlaconParams) : Config
}