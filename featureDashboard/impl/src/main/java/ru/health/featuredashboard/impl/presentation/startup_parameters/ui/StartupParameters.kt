package ru.health.featuredashboard.impl.presentation.startup_parameters.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import dev.chrisbanes.haze.hazeSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.health.core.api.domain.DeviceType
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LocalHazeState
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersAction
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersUiState
import ru.health.featuredashboard.impl.presentation.startup_parameters.ui.consumption.FillConsumption
import ru.health.featuredashboard.impl.presentation.startup_parameters.ui.device.SelectDeviceType
import ru.health.featuredashboard.impl.presentation.startup_parameters.ui.fill_liquid.FillLiquid
import ru.health.featuredashboard.impl.presentation.startup_parameters.ui.interests.SelectInterests

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun StartupParameters(
    modifier: Modifier = Modifier,
    state: StartupParametersUiState,
    pagerState: PagerState = rememberPagerState { 3 },
    onAction: (action: StartupParametersAction) -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()

    GradientBox(
        modifier = Modifier
            .fillMaxSize()
            .hazeSource(LocalHazeState.current)
    )

    val selectInterests: @Composable () -> Unit = {
        SelectInterests(
            interests = state.interests,
            onSelect = { onAction(StartupParametersAction.SelectInterest(it)) },
            onProceed = { onAction(StartupParametersAction.OnFinished) }
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = false,
            state = pagerState
        ) { index ->
            when (index) {
                0 -> {
                    SelectDeviceType(
                        state = state,
                        onAction = onAction,
                        onProceed = {
                            coroutineScope.scrollToNextPage(pagerState)
                        }
                    )
                }
                1 -> {
                    FillConsumption(
                        state = state,
                        onAction = onAction,
                        onProceed = {
                            coroutineScope.scrollToNextPage(pagerState)
                        }
                    )
                }
                2 -> {
                    if (state.deviceType == DeviceType.POD) {
                        FillLiquid(
                            state = state,
                            onAction = onAction,
                            onProceed = {
                                coroutineScope.scrollToNextPage(pagerState)
                            }
                        )
                    } else selectInterests()
                }
                3 -> selectInterests()
            }
        }
    }
}

private fun CoroutineScope.scrollToNextPage(pagerState: PagerState) = launch {
    pagerState.animateScrollToPage(
        page = pagerState.currentPage + 1,
        animationSpec = tween(400)
    )
}

internal val startupParametersUiStatePreview by lazy {
    StartupParametersUiState()
}

@PreviewLightDark
@Composable
private fun StartupParametersPreview() {
    AirlyTheme {
        StartupParameters(state = startupParametersUiStatePreview)
    }
}