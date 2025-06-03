package ru.health.featureliquid.impl.presentation.detail.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featureliquid.api.domain.model.BottleType
import ru.health.featureliquid.api.domain.model.DeviceType
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailAction
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailUiState
import ru.health.featureliquid.impl.presentation.detail.ui.bottle.Bottle
import ru.health.featureliquid.impl.presentation.detail.ui.disposable.Disposable

@Composable
internal fun LiquidDetail(
    modifier: Modifier = Modifier,
    state: LiquidDetailUiState,
    onAction: (LiquidDetailAction) -> Unit = {}
) {
    AnimatedContent(
        targetState = state.deviceType,
        transitionSpec = {
            if (targetState is DeviceType.Disposable) {
                slideInHorizontally { width -> width } togetherWith
                        slideOutHorizontally { width -> -width }
            } else {
                slideInHorizontally { width -> -width } togetherWith
                        slideOutHorizontally { width -> width }
            }.using(
                SizeTransform(clip = false)
            )
        }
    ) { deviceType ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            val contentModifier = Modifier.align(Alignment.BottomStart)

            if (deviceType is DeviceType.Liquid) {
                Bottle(
                    modifier = contentModifier,
                    liquid = deviceType
                )
            } else {
                Disposable(
                    modifier = contentModifier.fillMaxWidth(0.5f)
                )
            }
        }
    }
}

internal val liquidPreview = DeviceType.Liquid(
    id = 1,
    bottleType = BottleType.SMALL,
    currentVolume = 25f
)

internal val disposablePreview = DeviceType.Disposable(
    id = 1
)

internal val liquidDetailUiStatePreview = LiquidDetailUiState(
    deviceType = liquidPreview
)

@PreviewLightDark
@Composable
private fun LiquidDetailPreview() {
    AirlyTheme {
        GradientBox {
            LiquidDetail(
                state = liquidDetailUiStatePreview
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun LiquidDetailDisposablePreview() {
    AirlyTheme {
        GradientBox {
            LiquidDetail(
                state = liquidDetailUiStatePreview.copy(
                    deviceType = disposablePreview
                )
            )
        }
    }
}