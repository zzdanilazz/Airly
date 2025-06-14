package ru.health.featureliquid.impl.presentation.input.ui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

    val context = LocalContext.current
    DisposableEffect(Unit) {
        val window = (context as? Activity)?.window
        val originalBrightness = window?.attributes?.screenBrightness ?: -1f

        window?.attributes = window.attributes?.apply {
            screenBrightness = 1f
        }

        onDispose {
            window?.attributes = window.attributes?.apply {
                screenBrightness = originalBrightness
            }
        }
    }

    InputLiquidScaffold(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}