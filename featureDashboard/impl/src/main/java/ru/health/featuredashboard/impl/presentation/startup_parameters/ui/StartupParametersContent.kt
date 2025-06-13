package ru.health.featuredashboard.impl.presentation.startup_parameters.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersAction
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersViewModel

@Composable
internal fun StartupParametersContent(
    modifier: Modifier = Modifier,
    viewModel: StartupParametersViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onAction(StartupParametersAction.Init)
    }

    StartupParametersScaffold(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}