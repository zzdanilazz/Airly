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
import ru.health.featureliquid.api.domain.model.BottleType
import ru.health.featureliquid.api.domain.model.DeviceType
import ru.health.featureliquid.impl.presentation.detail.ui.liquidPreview

@Composable
internal fun OutlinedBottle(
    modifier: Modifier = Modifier,
    liquid: DeviceType.Liquid,
    editedVolume: Float
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        FilledCap(
            modifier = Modifier.zIndex(1f),
            bottleType = liquid.bottleType
        )
        OutlinedFlacon(
            bottleType = liquid.bottleType,
            currentVolume = liquid.currentVolume,
            editedVolume = editedVolume
        )
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