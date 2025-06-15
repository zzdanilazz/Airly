package ru.health.featureliquid.api.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Consumption(
    val id: Int = 0,
    val frequency: Float,
    val durationInDays: Float,
    val isMeasured: Boolean = false,
    val deviceId: Int,
    val date: String
)