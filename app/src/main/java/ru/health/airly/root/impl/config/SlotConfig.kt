package ru.health.airly.root.impl.config

import kotlinx.serialization.Serializable

@Serializable
sealed interface SlotConfig {

    @Serializable
    data object Approve : SlotConfig
}