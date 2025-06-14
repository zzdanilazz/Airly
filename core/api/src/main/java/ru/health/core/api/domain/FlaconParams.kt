package ru.health.core.api.domain

import kotlinx.serialization.Serializable

@Serializable
data class FlaconParams(
    val volume: Float,
    val flaconType: FlaconType
)