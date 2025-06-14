package ru.health.featureliquid.impl.presentation.root

import kotlinx.serialization.Serializable

@Serializable
sealed interface LiquidConfig {

    @Serializable
    data object LiquidDetail : LiquidConfig
}