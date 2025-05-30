package ru.health.featureliquid.impl.presentation.detail.ui.top

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.health.core.impl.presentation.ui.theme.AirlyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LiquidDetailTopBar(
    modifier: Modifier = Modifier,
    isPodSelected: Boolean,
    onSwitch: (Boolean) -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Color.Transparent
        ),
        title = {
            DeviceSwitch(
                isPodSelected = isPodSelected,
                onSwitch = onSwitch
            )
        }
    )
}

@Composable
@Preview
private fun LiquidDetailTopBarPodSelectedPreview() {
    AirlyTheme {
        LiquidDetailTopBar(isPodSelected = true)
    }
}

@Composable
@Preview
private fun LiquidDetailTopBarDisposableSelectedPreview() {
    AirlyTheme {
        LiquidDetailTopBar(isPodSelected = false)
    }
}