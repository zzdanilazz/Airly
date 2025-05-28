package ru.health.featuredashboard.presentation.ui

import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.featuredashboard.presentation.DashboardAction
import ru.health.featuredashboard.presentation.DashboardUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DashboardScaffold(
    modifier: Modifier = Modifier,
    state: DashboardUiState,
    onAction: (action: DashboardAction) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            AddActionButton {
                onAction(DashboardAction.OnAddActionClick)
            }
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.only(
            WindowInsetsSides.Horizontal
        ),
        containerColor = Color.Transparent
    ) { padding ->
        Dashboard(
            modifier = Modifier.padding(padding),
            state = state,
            onAction = onAction
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