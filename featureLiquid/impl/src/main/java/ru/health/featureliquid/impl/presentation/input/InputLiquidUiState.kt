package ru.health.featureliquid.impl.presentation.input

import androidx.compose.runtime.Immutable
import ru.health.core.api.domain.FlaconParams

@Immutable
internal data class InputLiquidUiState(
    val flaconParams: FlaconParams,
    val editedVolume: Float = flaconParams.volume
)