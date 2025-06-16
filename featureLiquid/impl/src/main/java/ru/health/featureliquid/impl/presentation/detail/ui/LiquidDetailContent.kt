package ru.health.featureliquid.impl.presentation.detail.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.chrisbanes.haze.hazeSource
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.LocalHazeState
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailViewModel

@Composable
internal fun LiquidDetailContent(
    modifier: Modifier = Modifier,
    viewModel: LiquidDetailViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GradientBox(
        modifier = Modifier
            .fillMaxSize()
            .hazeSource(LocalHazeState.current)
    )

    LiquidDetailScaffold(
        modifier = modifier.hazeSource(LocalHazeState.current),
        state = state,
        onAction = viewModel::onAction
    )
}