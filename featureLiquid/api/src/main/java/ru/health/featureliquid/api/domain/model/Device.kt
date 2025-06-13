package ru.health.featureliquid.api.domain.model

import kotlinx.serialization.Serializable
import ru.health.core.api.domain.BottleType
import ru.health.core.api.domain.DeviceType

@Serializable
data class Device(
    val id: Int,
    val deviceType: DeviceType,
    val bottleType: BottleType? = null,
    val currentVolume: Float? = null
)