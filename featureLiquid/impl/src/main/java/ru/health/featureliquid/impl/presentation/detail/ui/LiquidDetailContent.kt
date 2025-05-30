package ru.health.featureliquid.impl.presentation.detail.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailAction
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailViewModel

@Composable
internal fun LiquidDetailContent(
    modifier: Modifier = Modifier,
    viewModel: LiquidDetailViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onAction(LiquidDetailAction.Init)
    }

    GradientBox(modifier = Modifier.fillMaxSize())

    LiquidDetailScaffold(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}