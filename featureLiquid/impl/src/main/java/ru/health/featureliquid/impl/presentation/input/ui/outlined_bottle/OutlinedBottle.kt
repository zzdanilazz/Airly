package ru.health.featureliquid.impl.presentation.input.ui.outlined_bottle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.zIndex
import ru.health.core.api.domain.FlaconParams
import ru.health.core.api.domain.FlaconType
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featureliquid.impl.presentation.input.ui.flaconParamsPreview

@Composable
internal fun OutlinedBottle(
    modifier: Modifier = Modifier,
    flaconParams: FlaconParams,
    editedVolume: Float
) {
    val bottleType = flaconParams.flaconType
    val currentVolume = flaconParams.volume

    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        FilledCap(
            modifier = Modifier.zIndex(1f),
            flaconType = bottleType
        )
        OutlinedFlacon(
            flaconType = bottleType,
            currentVolume = currentVolume,
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
                flaconParams = flaconParamsPreview,
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
                flaconParams = flaconParamsPreview.copy(
                    flaconType = FlaconType.TALL
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
                flaconParams = flaconParamsPreview.copy(
                    flaconType = FlaconType.LARGE
                ),
                editedVolume = 10f
            )
        }
    }
}