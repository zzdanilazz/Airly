package ru.health.featureliquid.api.domain.model

sealed interface VapeProduct {

    val isPodSelected
        get() = this is Liquid

    data class Liquid(
        val id: Int,
        val bottleType: BottleType,
        val currentVolume: Int
    ) : VapeProduct

    data class Disposable(
        val id: Int,
        val totalPuffs: Int,
        val currentPuffs: Int
    ) : VapeProduct
}