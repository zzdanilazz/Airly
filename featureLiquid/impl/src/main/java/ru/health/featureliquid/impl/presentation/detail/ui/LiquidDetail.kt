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
import ru.health.featureliquid.api.domain.model.Device
import ru.health.core.api.domain.DeviceType
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailAction
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailUiState
import ru.health.featureliquid.impl.presentation.detail.ui.bottle.Bottle
import ru.health.featureliquid.impl.presentation.detail.ui.disposable.Disposable
import ru.health.featureliquid.impl.presentation.input.ui.flaconParamsPreview

@Composable
internal fun LiquidDetail(
    modifier: Modifier = Modifier,
    state: LiquidDetailUiState,
    onAction: (LiquidDetailAction) -> Unit = {}
) {
    AnimatedContent(
        targetState = state.device,
        transitionSpec = {
            if (targetState?.deviceType == DeviceType.DISPOSABLE) {
                slideInHorizontally { width -> width } togetherWith
                        slideOutHorizontally { width -> -width }
            } else {
                slideInHorizontally { width -> -width } togetherWith
                        slideOutHorizontally { width -> width }
            }.using(
                SizeTransform(clip = false)
            )
        }
    ) { device ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            val contentModifier = Modifier.align(Alignment.BottomStart)

            device?.let {
                if (it.deviceType == DeviceType.POD) {
                    Bottle(
                        modifier = contentModifier,
                        pod = it
                    )
                } else {
                    Disposable(
                        modifier = contentModifier.fillMaxWidth(0.5f)
                    )
                }
            }
        }
    }
}

internal val liquidPreview = Device(
    id = 1,
    flaconParams = flaconParamsPreview,
    deviceType = DeviceType.POD,
    date = "31.10.2024",
    price = 500
)

internal val disposablePreview = Device(
    id = 1,
    deviceType = DeviceType.DISPOSABLE,
    date = "31.10.2024",
    price = 2_000
)

internal val liquidDetailUiStatePreview = LiquidDetailUiState(
    device = liquidPreview
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
                    device = disposablePreview
                )
            )
        }
    }
}