package ru.health.featureliquid.impl.presentation.input.ui.outlined_bottle

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.api.domain.FlaconType
import kotlin.math.sqrt

@Composable
internal fun OutlinedFlacon(
    modifier: Modifier = Modifier,
    flaconType: FlaconType,
    currentVolume: Float,
    editedVolume: Float
) {
    val flaconShape = RoundedCornerShape(26.dp)
    val liquidShape = RoundedCornerShape(18.dp)
    val editedLiquidFraction = editedVolume / flaconType.volume
    val currentLiquidFraction = currentVolume / flaconType.volume
    val totalSize = flaconType.flaconSize

    Box(
        modifier = modifier
            .size(totalSize)
            .clip(flaconShape)
            .border(BorderWidth, Color.White, flaconShape),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(BorderWidth * 2)
                .clip(liquidShape),
            contentAlignment = Alignment.BottomCenter
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(editedLiquidFraction)
                    .clip(liquidShape)
                    .background(Color.White)
            )

            Column(
                modifier = Modifier.fillMaxHeight(currentLiquidFraction),
            ) {
                Line()
            }
        }
    }
}

private val BorderWidth = 4.dp

internal val FlaconType.flaconSize: DpSize
    @Composable
    get() = when (this) {
        FlaconType.SMALL -> DpSize(width = mmToDp(30f), height = mmToDp(50f))
        FlaconType.TALL -> DpSize(width = mmToDp(30f), height = mmToDp(100f))
        FlaconType.LARGE -> DpSize(width = mmToDp(30f) * sqrt(2f), height = mmToDp(100f))
    }

@Composable
@PreviewLightDark
private fun OutlinedFlaconPreview() {
    AirlyTheme {
        GradientBox(blurred = true) {
            OutlinedFlacon(
                modifier = Modifier.align(Alignment.Center),
                flaconType = FlaconType.SMALL,
                currentVolume = 28f,
                editedVolume = 25f
            )
        }
    }
}