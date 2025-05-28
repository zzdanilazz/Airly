package ru.health.core.presentation.ui.gradient

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import ru.health.core.presentation.ui.theme.DarkBlue

@Composable
fun GradientBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val topBrush = Brush.verticalGradient(
        List(5) { index ->
            DarkBlue.copy(alpha = 1f - index * 0.2f)
        }
    )
    val bottomBrush = Brush.verticalGradient(
        List(5) { index ->
            DarkBlue.copy(alpha = 0.2f + index * 0.2f)
        }
    )
    val containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    Box(modifier = modifier.background(containerColor)) {
        Column(modifier = Modifier.fillMaxSize()) {
            val canvasModifier = Modifier.fillMaxWidth()
            Canvas(
                modifier = canvasModifier.weight(7f),
                onDraw = {
                    drawRect(topBrush)
                }
            )
            Canvas(
                modifier = canvasModifier.weight(3f),
                onDraw = {
                    drawRect(bottomBrush)
                }
            )
        }
        content()
    }
}