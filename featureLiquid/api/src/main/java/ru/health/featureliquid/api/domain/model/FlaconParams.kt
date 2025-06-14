package ru.health.featureliquid.api.domain.model

import kotlinx.serialization.Serializable
import ru.health.core.api.domain.FlaconType

@Serializable
data class FlaconParams(
    val volume: Float,
    val flaconType: FlaconType
)