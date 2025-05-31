package ru.health.featureliquid.impl.presentation.detail.ui.disposable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.theme.DarkBlue
import ru.health.core.impl.presentation.ui.theme.VeryDarkGray

@Composable
internal fun DisposableBottom(modifier: Modifier = Modifier) {
    val lightBrush = Brush.verticalGradient(
        listOf(DarkBlue, DarkBlue.copy(alpha = 0.3f))
    )
    val darkBrush = Brush.horizontalGradient(
        listOf(Color.Black, VeryDarkGray)
    )
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Spacer(
            modifier = Modifier
                .size(width = DisposableBodyWidth, height = 12.dp)
                .background(darkBrush, RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp))
        )
        Spacer(
            modifier = Modifier
                .size(width = DisposableBodyWidth, height = 5.dp)
                .background(Color.White.copy(alpha = 0.2f))
        )
        Spacer(
            modifier = Modifier
                .size(width = DisposableBodyWidth, height = 5.dp)
                .background(lightBrush)
        )
    }
}