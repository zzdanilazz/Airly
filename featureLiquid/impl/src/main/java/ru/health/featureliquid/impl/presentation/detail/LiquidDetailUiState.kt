package ru.health.featureliquid.impl.presentation.detail

import androidx.compose.runtime.Immutable
import ru.health.featureliquid.api.domain.model.VapeProduct

@Immutable
internal data class LiquidDetailUiState(
    val vapeProduct: VapeProduct? = null
)