package ru.health.featuredashboard.impl.presentation.dashboard.ui.airly

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featuredashboard.impl.R

@Composable
internal fun Airly(
    modifier: Modifier = Modifier,
    health: Int
) {
    val infinite = rememberInfiniteTransition()

    val animateFloat by infinite.animateFloat(
        initialValue = 0f,
        targetValue = if (health > 0) 6f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = health * 12, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val colorFilter = ColorFilter.tint(
        color = Color.DarkGray.copy(alpha = 1 - health / 100f),
        blendMode = BlendMode.SrcAtop
    )

    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .rotate(animateFloat)
                .offset(y = (65).dp),
            colorFilter = colorFilter,
            painter = painterResource(R.drawable.illustration_left_hand),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .rotate(-animateFloat)
                .offset(x= 5.dp,y = (65).dp),
            colorFilter = colorFilter,
            painter = painterResource(R.drawable.illustration_right_hand),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (50).dp),
            colorFilter = colorFilter,
            painter = painterResource(R.drawable.illustration_legs),
            contentDescription = null
        )
        Box(
            modifier = Modifier.offset(y = animateFloat.dp)
        ) {
            Image(
                colorFilter = colorFilter,
                painter = painterResource(R.drawable.illustration_body),
                contentDescription = null
            )
            Row(
                modifier = Modifier.align(Alignment.Center),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(2) {
                    Eye(health = health)
                }
            }
        }
    }
}

@Preview
@Composable
private fun AirlyFullPreview() {
    AirlyTheme {
        Box(
            modifier = Modifier.size(500.dp),
            contentAlignment = Alignment.Center
        ) {
            Airly(health = 100)
        }
    }
}

@Preview
@Composable
private fun AirlyStrongPreview() {
    AirlyTheme {
        Airly(health = 80)
    }
}

@Preview
@Composable
private fun AirlyHalfPreview() {
    AirlyTheme {
        Airly(health = 50)
    }
}

@Preview
@Composable
private fun AirlyLowPreview() {
    AirlyTheme {
        Airly(health = 20)
    }
}

@Preview
@Composable
private fun AirlyDeadPreview() {
    AirlyTheme {
        Airly(health = 0)
    }
}