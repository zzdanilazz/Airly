package ru.health.featureliquid.impl.presentation.input.ui.outlined_bottle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.theme.LightRed
import kotlin.math.roundToInt

@Composable
internal fun Line(
    modifier: Modifier = Modifier,
    lineWidth: Dp = DefaultLineWidth
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(lineWidth)
            .background(
                color = LightRed,
                shape = DottedShape(step = 10.dp)
            )
    )
}

private data class DottedShape(val step: Dp) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ) = Outline.Generic(Path().apply {
        val stepPx = with(density) { step.toPx() }
        val stepsCount = (size.width / stepPx).roundToInt()
        val actualStep = size.width / stepsCount
        val dotSize = Size(width = actualStep / 2, height = size.height)
        for (i in 0 until stepsCount) {
            addRect(
                Rect(
                    offset = Offset(x = i * actualStep, y = 0f),
                    size = dotSize
                )
            )
        }
        close()
    })
}

internal val DefaultLineWidth = 4.dp