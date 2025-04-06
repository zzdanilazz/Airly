package ru.health.featuredashboard.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.health.core.presentation.ui.theme.AirlyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DashboardScaffold(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            DashboardTopBar(
                name = "Данила"
            )
        }
    ) { padding ->
        Dashboard(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        )
    }
}

@PreviewLightDark
@Composable
private fun DashboardScaffoldPreview() {
    AirlyTheme {
        DashboardScaffold()
    }
}