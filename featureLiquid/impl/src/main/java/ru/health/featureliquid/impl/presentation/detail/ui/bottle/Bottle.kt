package ru.health.featureliquid.impl.presentation.detail.ui.bottle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.api.domain.BottleType
import ru.health.core.api.domain.Device
import ru.health.featureliquid.impl.presentation.detail.ui.liquidPreview

@Composable
internal fun Bottle(
    modifier: Modifier = Modifier,
    liquid: Device
) {
    val bottleType = liquid.bottleType
    val currentVolume = liquid.currentVolume

    Column(modifier = modifier) {
        if (bottleType != null && currentVolume != null) {
            Cap(
                modifier = Modifier.zIndex(1f),
                bottleType = bottleType
            )

            Flacon(
                modifier = Modifier.offset(y = (-30).dp),
                bottleType = bottleType,
                currentVolume = currentVolume
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun SmallBottlePreview() {
    AirlyTheme {
        GradientBox {
            Bottle(
                modifier = Modifier.align(Alignment.Center),
                liquid = liquidPreview
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun TallBottlePreview() {
    AirlyTheme {
        GradientBox {
            Bottle(
                modifier = Modifier.align(Alignment.Center),
                liquid = liquidPreview.copy(
                    bottleType = BottleType.TALL
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun LargeBottlePreview() {
    AirlyTheme {
        GradientBox {
            Bottle(
                modifier = Modifier.align(Alignment.Center),
                liquid = liquidPreview.copy(
                    bottleType = BottleType.LARGE
                )
            )
        }
    }
}