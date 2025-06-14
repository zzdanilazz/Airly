package ru.health.core.api.domain

import kotlinx.serialization.Serializable

@Serializable
data class Device(
    val id: Int = 0,
    val deviceType: DeviceType,
    val bottleType: BottleType? = null,
    val currentVolume: Float? = null
)