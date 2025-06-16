package ru.health.featuredashboard.impl.presentation.dashboard.ui

import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featuredashboard.impl.presentation.dashboard.DashboardAction
import ru.health.featuredashboard.impl.presentation.dashboard.DashboardUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DashboardScaffold(
    modifier: Modifier = Modifier,
    state: DashboardUiState,
    onAction: (action: DashboardAction) -> Unit = {}
) {
    val graphicsLayer = rememberGraphicsLayer()

    Scaffold(
        modifier = modifier.drawWithContent {
            graphicsLayer.record {
                this@drawWithContent.drawContent()
            }
            drawLayer(graphicsLayer)
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.only(
            WindowInsetsSides.Horizontal
        ),
        containerColor = Color.Transparent
    ) { padding ->
        Dashboard(
            modifier = Modifier.padding(padding),
            state = state,
            onAction = onAction,
            graphicsLayer = graphicsLayer
        )
    }
}

@PreviewLightDark
@Composable
private fun DashboardScaffoldPreview() {
    AirlyTheme {
        DashboardScaffold(state = dashboardUiStatePreview)
    }
}