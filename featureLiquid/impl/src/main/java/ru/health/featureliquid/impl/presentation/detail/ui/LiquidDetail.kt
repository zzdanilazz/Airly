package ru.health.featureliquid.impl.presentation.detail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featureliquid.api.domain.model.BottleType
import ru.health.featureliquid.api.domain.model.VapeProduct
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailAction
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailUiState
import ru.health.featureliquid.impl.presentation.detail.ui.bottle.Bottle

@Composable
internal fun LiquidDetail(
    modifier: Modifier = Modifier,
    state: LiquidDetailUiState,
    onAction: (LiquidDetailAction) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        if (state.vapeProduct is VapeProduct.Liquid) {
            Bottle(
                modifier = Modifier.align(Alignment.BottomStart),
                liquid = state.vapeProduct
            )
        }
    }
}

internal val liquidPreview = VapeProduct.Liquid(
    id = 1,
    bottleType = BottleType.SMALL,
    currentVolume = 13
)

internal val liquidDetailUiStatePreview = LiquidDetailUiState(
    vapeProduct = liquidPreview
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