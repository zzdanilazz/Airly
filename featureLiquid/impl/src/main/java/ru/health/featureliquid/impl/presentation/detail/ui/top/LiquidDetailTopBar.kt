package ru.health.featureliquid.impl.presentation.detail.ui.top

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.health.core.api.domain.DeviceType
import ru.health.core.impl.presentation.iconResId
import ru.health.core.impl.presentation.ui._switch.SemitransparentSwitch
import ru.health.core.impl.presentation.ui.theme.AirlyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LiquidDetailTopBar(
    modifier: Modifier = Modifier,
    selectedDeviceType: DeviceType = DeviceType.POD,
    onSwitch: (deviceType: DeviceType) -> Unit = {}
) {
    val targetItems = DeviceType.entries
        .filter { it.isPrimary }
        .map { it to it.iconResId }

    val selectedIndex = targetItems.indexOfFirst {
        it.first == selectedDeviceType
    }

    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Color.Transparent
        ),
        title = {
            SemitransparentSwitch(
                items = targetItems,
                selectedIndex = selectedIndex,
                onSwitch = onSwitch
            )
        }
    )
}

@Composable
@Preview
private fun LiquidDetailTopBarPodSelectedPreview() {
    AirlyTheme {
        LiquidDetailTopBar(selectedDeviceType = DeviceType.POD)
    }
}

@Composable
@Preview
private fun LiquidDetailTopBarDisposableSelectedPreview() {
    AirlyTheme {
        LiquidDetailTopBar(selectedDeviceType = DeviceType.DISPOSABLE)
    }
}