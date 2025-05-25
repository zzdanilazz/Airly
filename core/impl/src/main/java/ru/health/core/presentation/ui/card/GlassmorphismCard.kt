package ru.health.core.presentation.ui.card

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import ru.health.core.presentation.ui.theme.AirlyTheme

@Composable
fun GlassmorphismCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val hazeState = rememberHazeState()

    Card(
        modifier = modifier.hazeSource(hazeState),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White.copy(alpha = 0.3f),
            contentColor = Color.White
        ),
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