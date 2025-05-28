package ru.health.featurestatistics.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.health.core.presentation.ui.gradient.GradientBox
import ru.health.core.presentation.ui.theme.AirlyTheme

@Composable
internal fun StatisticsContent(
    modifier: Modifier = Modifier,
) {
    GradientBox(modifier = Modifier.fillMaxSize())
}

@PreviewLightDark
@Composable
private fun StatisticsContentPreview() {
    AirlyTheme {
        StatisticsContent()
    }
}