package ru.health.featurestatistics.presentation.ui.liquid

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.featurestatistics.impl.R

@Composable
internal fun LiquidRow(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.height(intrinsicSize = IntrinsicSize.Max)
    ) {
        LiquidCard(
            iconRes = R.drawable.ic_liquid,
            titleRes = R.string.liquid_consumption,
            consumption = "300 мл"
        )
        Spacer(modifier = Modifier.width(8.dp))
        LiquidCard(
            iconRes = R.drawable.ic_nicotine,
            titleRes = R.string.nicotine_consumption,
            consumption = "123.9 мг"
        )
    }
}

@PreviewLightDark
@Composable
private fun LiquidRowPreview() {
    AirlyTheme {
        LiquidRow()
    }
}