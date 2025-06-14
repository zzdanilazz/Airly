package ru.health.featureliquid.impl.presentation.input.ui.outlined_bottle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.zIndex
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.api.domain.BottleType
import ru.health.core.api.domain.Device
import ru.health.featureliquid.impl.presentation.detail.ui.liquidPreview

@Composable
internal fun OutlinedBottle(
    modifier: Modifier = Modifier,
    liquid: Device,
    editedVolume: Float
) {
    val bottleType = liquid.bottleType
    val currentVolume = liquid.currentVolume

    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        if (bottleType != null && currentVolume != null) {
            FilledCap(
                modifier = Modifier.zIndex(1f),
                bottleType = bottleType
            )
            OutlinedFlacon(
                bottleType = bottleType,
                currentVolume = currentVolume,
                editedVolume = editedVolume
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun SmallOutlinedBottlePreview() {
    AirlyTheme {
        GradientBox {
            OutlinedBottle(
                modifier = Modifier.align(Alignment.Center),
                liquid = liquidPreview,
                editedVolume = 10f
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun TallOutlinedBottlePreview() {
    AirlyTheme {
        GradientBox {
            OutlinedBottle(
                modifier = Modifier.align(Alignment.Center),
                liquid = liquidPreview.copy(
                    bottleType = BottleType.TALL
                ),
                editedVolume = 10f
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun LargeOutlinedBottlePreview() {
    AirlyTheme {
        GradientBox {
            OutlinedBottle(
                modifier = Modifier.align(Alignment.Center),
                liquid = liquidPreview.copy(
                    bottleType = BottleType.LARGE
                ),
                editedVolume = 10f
            )
        }
    }
}