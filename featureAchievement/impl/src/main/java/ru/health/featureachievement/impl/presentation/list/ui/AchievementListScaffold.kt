package ru.health.featureachievement.impl.presentation.list.ui

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
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featureachievement.impl.presentation.list.AchievementListAction
import ru.health.featureachievement.impl.presentation.list.AchievementListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AchievementListScaffold(
    modifier: Modifier = Modifier,
    state: AchievementListUiState,
    onAction: (action: AchievementListAction) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.only(
            WindowInsetsSides.Horizontal
        ),
        containerColor = Color.Transparent
    ) { padding ->
        AchievementList(
            modifier = Modifier.padding(padding),
            state = state,
            onAction = onAction
        )
    }
}

@PreviewLightDark
@Composable
private fun AchievementListScaffoldPreview() {
    AirlyTheme {
        AchievementListScaffold(state = achievementListUiStatePreview)
    }
}