package ru.health.featureliquid.impl.presentation.input

import androidx.compose.runtime.Immutable
import ru.health.featureliquid.api.domain.model.DeviceType

@Immutable
internal data class InputLiquidUiState(
    val liquid: DeviceType.Liquid,
    val editedVolume: Float = liquid.currentVolume
)