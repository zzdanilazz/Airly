package ru.health.featuredashboard.impl.presentation.dashboard.ui.airly

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.theme.AirlyTheme

@Composable
internal fun Eye(
    modifier: Modifier = Modifier,
    health: Int = 100
) {
    val shape = CircleShape
    val infinite = rememberInfiniteTransition()
    val offsetX by infinite.animateFloat(
        initialValue = 1f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = health * 10, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val blinkDuration = 3000
    val pupilHeight by infinite.animateFloat(
        initialValue = 22f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = blinkDuration
                22f at (blinkDuration * 80 / 100)
                0f at (blinkDuration * 85 / 100)
                22f at (blinkDuration * 90 / 100)
            },
            repeatMode = RepeatMode.Restart
        )
    )
    Box(
        modifier = modifier
        .width(22.dp)
        .height(pupilHeight.dp)
        .clip(shape)
        .background(Color.Black)
    ) {
        Spacer(
            modifier = Modifier
                .offset(x = offsetX.dp, y = 2.dp)
                .size(16.dp)
                .clip(shape)
                .background(Color.White)
        )
    }
}

@Composable
@Preview
private fun EyePreview() {
    AirlyTheme {
        Eye()
    }
}