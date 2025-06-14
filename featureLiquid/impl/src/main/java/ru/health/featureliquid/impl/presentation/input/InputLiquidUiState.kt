package ru.health.featureliquid.impl.presentation.input

import androidx.compose.runtime.Immutable
import ru.health.featureliquid.api.domain.model.FlaconParams

@Immutable
internal data class InputLiquidUiState(
    val isPositiveVolume: Boolean = false,
    val flaconParams: FlaconParams,
    val editedVolume: Float = flaconParams.volume
)