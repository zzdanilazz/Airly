package ru.health.featureliquid.impl.presentation.input.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import dev.chrisbanes.haze.hazeSource
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LocalHazeState
import ru.health.featureliquid.impl.presentation.input.InputLiquidAction
import ru.health.featureliquid.impl.presentation.input.InputLiquidUiState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InputLiquidScaffold(
    modifier: Modifier = Modifier,
    state: InputLiquidUiState,
    onAction: (action: InputLiquidAction) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        topBar = {
            InputLiquidTopBar {
                onAction(InputLiquidAction.Back)
            }
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.only(
            WindowInsetsSides.Horizontal
        )
    ) { _ ->
        InputLiquid(
            modifier = Modifier.hazeSource(LocalHazeState.current),
            state = state,
            onAction = onAction
        )
    }
}

@PreviewLightDark
@Composable
private fun InputLiquidScaffoldPreview() {
    AirlyTheme {
        GradientBox(blurred = true)
        InputLiquidScaffold(state = inputLiquidUiStatePreview)
    }
}