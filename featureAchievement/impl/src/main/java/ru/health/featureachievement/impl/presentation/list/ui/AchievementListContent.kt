package ru.health.featureachievement.impl.presentation.list.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.chrisbanes.haze.hazeSource
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.LocalHazeState
import ru.health.featureachievement.impl.presentation.list.AchievementListAction
import ru.health.featureachievement.impl.presentation.list.AchievementListViewModel

@Composable
internal fun AchievementListContent(
    modifier: Modifier = Modifier,
    viewModel: AchievementListViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.onAction(AchievementListAction.Init)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    GradientBox(
        modifier = Modifier
            .fillMaxSize()
            .hazeSource(LocalHazeState.current)
    )

    AchievementListScaffold(
        modifier = modifier.hazeSource(LocalHazeState.current),
        state = state,
        onAction = viewModel::onAction
    )
}