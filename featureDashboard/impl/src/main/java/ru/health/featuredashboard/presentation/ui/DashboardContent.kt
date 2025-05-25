package ru.health.featuredashboard.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.health.featuredashboard.presentation.DashboardAction
import ru.health.featuredashboard.presentation.DashboardViewModel

@Composable
internal fun DashboardContent(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onAction(DashboardAction.Init)
    }

    DashboardScaffold(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}