package ru.health.featuredashboard.impl.presentation.dashboard.ui.airly

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featuredashboard.impl.R

@Composable
internal fun Airly(
    modifier: Modifier = Modifier,
    health: Int
) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_airly),
        contentDescription = null
    )
}

@PreviewLightDark
@Composable
private fun AirlyPreview() {
    AirlyTheme {
        Airly(health = 86)
    }
}