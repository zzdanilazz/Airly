package ru.health.featuredashboard.impl.presentation.startup_parameters.ui

import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import dev.chrisbanes.haze.hazeSource
import ru.health.core.api.domain.DeviceType
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LocalHazeState
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersAction
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun StartupParametersScaffold(
    modifier: Modifier = Modifier,
    state: StartupParametersUiState,
    onAction: (action: StartupParametersAction) -> Unit = {}
) {
    val pagerState = rememberPagerState {
        if (state.deviceType == DeviceType.POD) 4 else 3
    }

    Scaffold(
        modifier = modifier,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.only(
            WindowInsetsSides.Horizontal
        ),
        containerColor = Color.Transparent
    ) { padding ->
        StartupParameters(
            modifier = Modifier
                .hazeSource(LocalHazeState.current)
                .padding(padding),
            state = state,
            pagerState = pagerState,
            onAction = onAction
        )
        StartupParametersTopBar(
            pagerState = pagerState,
            deviceType = state.deviceType
        )
    }
}

@PreviewLightDark
@Composable
private fun StartupParametersScaffoldPreview() {
    AirlyTheme {
        StartupParametersScaffold(state = startupParametersUiStatePreview)
    }
}