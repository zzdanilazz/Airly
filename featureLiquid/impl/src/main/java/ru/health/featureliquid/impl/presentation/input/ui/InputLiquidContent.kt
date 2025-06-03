package ru.health.featureliquid.impl.presentation.input.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.health.featureliquid.impl.presentation.input.InputLiquidAction
import ru.health.featureliquid.impl.presentation.input.InputLiquidViewModel

@Composable
internal fun InputLiquidContent(
    modifier: Modifier = Modifier,
    viewModel: InputLiquidViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onAction(InputLiquidAction.Init)
    }

    InputLiquidScaffold(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}