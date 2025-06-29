package ru.health.featureliquid.impl.presentation.detail.ui

import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.health.core.api.domain.DeviceType
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailAction
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailUiState
import ru.health.featureliquid.impl.presentation.detail.ui.fab.LiquidDetailFabGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LiquidDetailScaffold(
    modifier: Modifier = Modifier,
    state: LiquidDetailUiState,
    onAction: (action: LiquidDetailAction) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            state.primaryDevice?.let { device ->
                LiquidDetailFabGroup(
                    isPodSelected = device.deviceType == DeviceType.POD,
                    onAction = onAction
                )
            }
        },
        containerColor = Color.Transparent,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.only(
            WindowInsetsSides.Horizontal
        )
    ) { padding ->
        LiquidDetail(
            modifier = Modifier.padding(padding),
            state = state,
            onAction = onAction
        )
    }
}

@PreviewLightDark
@Composable
private fun LiquidDetailScaffoldPreview() {
    AirlyTheme {
        GradientBox()
        LiquidDetailScaffold(state = liquidDetailUiStatePreview)
    }
}

@PreviewLightDark
@Composable
private fun LiquidDetailDisposableScaffoldPreview() {
    AirlyTheme {
        GradientBox()
        LiquidDetailScaffold(state = liquidDetailUiStatePreview.copy(
            primaryDevice = disposablePreview
        ))
    }
}