package ru.health.core.api.domain

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