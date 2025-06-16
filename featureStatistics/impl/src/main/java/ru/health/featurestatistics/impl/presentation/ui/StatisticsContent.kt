package ru.health.featurestatistics.impl.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.featurestatistics.impl.presentation.StatisticsAction
import ru.health.featurestatistics.impl.presentation.StatisticsViewModel

@Composable
internal fun StatisticsContent(
    modifier: Modifier = Modifier,
    viewModel: StatisticsViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onAction(StatisticsAction.Init)
    }

    GradientBox(blurred = true) {
        StatisticsScaffold(
            modifier = modifier,
            state = state,
            onAction = viewModel::onAction
        )
    }
}