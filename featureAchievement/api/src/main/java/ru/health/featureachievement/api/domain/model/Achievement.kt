package ru.health.featureachievement.api.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class Achievement(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val level: Int? = null,
    val value: Int = 0,
    val thresholdValue: Int
) {
    val progress: Float
        get() = (value.toFloat() / thresholdValue).coerceAtMost(TARGET_PROGRESS)

    val isUnlocked: Boolean
        get() = progress == TARGET_PROGRESS

    companion object {
        private const val TARGET_PROGRESS = 1f
    }
}