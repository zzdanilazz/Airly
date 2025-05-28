package ru.health.core.presentation.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.core.presentation.ui.theme.LocalHazeState

@Composable
fun GlassmorphismCard(
    modifier: Modifier = Modifier,
    hazeState: HazeState = LocalHazeState.current,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val alpha = if (isSystemInDarkTheme()) 0.2f else 0.5f
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .hazeEffect(state = hazeState)
            .background(Color.White.copy(alpha = alpha)),
        content = content
    )
}

@Preview
@Composable
private fun GlassmorphismCardPreview() {
    AirlyTheme {
        GlassmorphismCard(modifier = Modifier.size(100.dp))
    }
}