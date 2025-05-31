package ru.health.featureliquid.api.domain.model

sealed interface DeviceType {

    val isPodSelected
        get() = this is Liquid

    data class Liquid(
        val id: Int,
        val bottleType: BottleType,
        val currentVolume: Int
    ) : DeviceType

    data class Disposable(
        val id: Int
    ) : DeviceType
}