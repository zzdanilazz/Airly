package ru.health.liquid.presentation.detail

import androidx.compose.runtime.Immutable
import ru.health.liquid.domain.model.VapeProduct

@Immutable
internal data class LiquidDetailUiState(
    val vapeProduct: VapeProduct? = null
)