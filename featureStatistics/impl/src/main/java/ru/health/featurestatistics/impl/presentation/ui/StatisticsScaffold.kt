package ru.health.featurestatistics.impl.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
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
import ru.health.featurestatistics.impl.presentation.StatisticsAction
import ru.health.featurestatistics.impl.presentation.StatisticsUiState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun StatisticsScaffold(
    modifier: Modifier = Modifier,
    state: StatisticsUiState,
    onAction: (action: StatisticsAction) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.only(
            WindowInsetsSides.Horizontal
        )
    ) { padding ->
        Statistics(
            modifier = Modifier
                .hazeSource(LocalHazeState.current)
                .padding(padding),
            state = state,
            onAction = onAction
        )
    }
}

@PreviewLightDark
@Composable
private fun StatisticsScaffoldPreview() {
    AirlyTheme {
        GradientBox(blurred = true)
        StatisticsScaffold(state = statisticsUiStatePreview)
    }
}