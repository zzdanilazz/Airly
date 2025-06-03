package ru.health.featureliquid.impl.presentation.detail.ui.bottle

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.health.featureliquid.api.domain.model.BottleType
import kotlin.math.sqrt

@Composable
internal fun Flacon(
    modifier: Modifier = Modifier,
    bottleType: BottleType,
    currentVolume: Float
) {
    val shape = RoundedCornerShape(26.dp)
    val liquidFraction = currentVolume / bottleType.volume
    val totalSize = bottleType.flaconSize
    val flaconColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Column(
        modifier = modifier
            .size(totalSize)
            .clip(shape)
    ) {
        if (liquidFraction < 1f) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f - liquidFraction, fill = true)
                    .background(flaconColor.copy(alpha = 0.25f))
            )
        }

        Liquid(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 32.dp)
                .fillMaxHeight(liquidFraction),
            currentVolume = currentVolume
        )
    }
}

internal val BottleType.flaconSize: DpSize
    get() = when (this) {
        BottleType.SMALL -> DpSize(width = 138.dp, height = 265.dp)
        BottleType.TALL -> DpSize(width = 138.dp, height = 530.dp)
        BottleType.LARGE -> DpSize(width = (138 * sqrt(2f)).dp, height = 530.dp)
    }