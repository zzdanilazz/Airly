package ru.health.featureliquid.api.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ConsumptionFrequency(
    val id: Int = 0,
    val value: Float,
    val deviceId: Int
)