package ru.health.airly.root.impl.config

import kotlinx.serialization.Serializable
import ru.health.featureliquid.api.domain.model.FlaconParams

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
    data class InputLiquid(
        val flaconParams: FlaconParams,
        val isPositiveVolume: Boolean = false
    ) : Config
}