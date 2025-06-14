package ru.health.featureliquid.impl.presentation.detail

import androidx.compose.runtime.Immutable
import ru.health.core.api.domain.Device

@Immutable
internal data class LiquidDetailUiState(
    val device: Device? = null
)