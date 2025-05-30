package ru.health.featuredashboard.api.domain

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class RelapseAction(
    val id: Int,
    val isAbstinence: Boolean,
    val date: String
)
