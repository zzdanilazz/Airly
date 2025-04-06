package ru.health.airly.root.impl.config

import kotlinx.serialization.Serializable

@Serializable
sealed interface Config {

    @Serializable
    data object Dashboard : Config
}