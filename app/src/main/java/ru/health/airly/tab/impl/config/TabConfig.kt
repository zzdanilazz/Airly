package ru.health.airly.tab.impl.config

import kotlinx.serialization.Serializable

@Serializable
sealed interface TabConfig {

    @Serializable
    data object Dashboard : TabConfig

    @Serializable
    data object InputLiquid : TabConfig

    @Serializable
    data object Statistics : TabConfig
}