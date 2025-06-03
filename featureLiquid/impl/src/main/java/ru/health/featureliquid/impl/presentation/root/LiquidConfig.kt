package ru.health.featureliquid.impl.presentation.root

import kotlinx.serialization.Serializable
import ru.health.featureliquid.api.domain.model.DeviceType

@Serializable
sealed interface LiquidConfig {

    @Serializable
    data object LiquidDetail : LiquidConfig

    @Serializable
    data class InputLiquid(val liquid: DeviceType.Liquid) : LiquidConfig
}