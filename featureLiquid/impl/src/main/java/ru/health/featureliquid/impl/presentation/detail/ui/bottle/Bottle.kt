package ru.health.featureliquid.impl.presentation.detail.ui.bottle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ru.health.featureliquid.api.domain.model.Device
import ru.health.core.api.domain.FlaconType
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featureliquid.impl.presentation.detail.ui.liquidPreview
import ru.health.featureliquid.impl.presentation.input.ui.flaconParamsPreview

@Composable
internal fun Bottle(
    modifier: Modifier = Modifier,
    pod: Device
) {
    Column(modifier = modifier) {
        pod.flaconParams?.let {
            Cap(
                modifier = Modifier.zIndex(1f),
                flaconType = it.flaconType
            )

            Flacon(
                modifier = Modifier.offset(y = (-30).dp),
                flaconType = it.flaconType,
                currentVolume = it.volume
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
                pod = liquidPreview
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
                pod = liquidPreview.copy(
                    flaconParams = flaconParamsPreview.copy(
                        flaconType = FlaconType.TALL
                    )
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
                pod = liquidPreview.copy(
                    flaconParams = flaconParamsPreview.copy(
                        flaconType = FlaconType.LARGE
                    )
                )
            )
        }
    }
}