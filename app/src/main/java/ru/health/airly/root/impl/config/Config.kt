package ru.health.airly.root.impl.config

import kotlinx.serialization.Serializable
import ru.health.core.api.domain.Device
import ru.health.featureliquid.impl.presentation.root.LiquidConfig

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
    data class InputLiquid(val liquid: Device) : Config
}