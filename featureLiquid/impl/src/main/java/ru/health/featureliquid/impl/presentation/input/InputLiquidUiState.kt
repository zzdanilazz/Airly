package ru.health.featureliquid.impl.presentation.input

import androidx.compose.runtime.Immutable
import ru.health.core.api.domain.Device

@Immutable
internal data class InputLiquidUiState(
    val liquid: Device,
    val editedVolume: Float = liquid.currentVolume ?: 0f
)