package ru.health.featureliquid.api.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class VapeAction(
    val id: Int,
    val puffs: Int,
    val deviceId: Int,
    val date: String
)