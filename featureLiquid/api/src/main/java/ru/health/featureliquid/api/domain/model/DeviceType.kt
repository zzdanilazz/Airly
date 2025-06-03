package ru.health.featureliquid.api.domain.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface DeviceType {

    val isPodSelected
        get() = this is Liquid

    @Serializable
    data class Liquid(
        val id: Int,
        val bottleType: BottleType,
        val currentVolume: Float
    ) : DeviceType

    @Serializable
    data class Disposable(
        val id: Int
    ) : DeviceType
}