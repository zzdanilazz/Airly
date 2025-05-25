package ru.health.airly.tab.impl.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.rememberHazeState
import ru.health.airly.tab.api.TabChild
import ru.health.airly.tab.api.TabComponent
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.featureachievement.impl.R as AchievementR
import ru.health.featuredashboard.impl.R as DashboardR
import ru.health.featureliquid.impl.R as LiquidR
import ru.health.featurestatistics.impl.R as StatisticsR

@Composable
internal fun BottomBar(modifier: Modifier = Modifier, component: TabComponent) {
    val childStack by component.stack.subscribeAsState()
    val activeComponent = childStack.active.instance
    val hazeState = rememberHazeState()

    NavigationBar(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp, 28.dp, 0.dp, 0.dp))
            .hazeEffect(hazeState),
        containerColor = Color.White.copy(alpha = 0.3f),
        tonalElevation = 0.dp
    ) {
        BottomTabItem(
            isSelected = activeComponent is TabChild.DashboardTab,
            painter = painterResource(DashboardR.drawable.ic_dashboard),
            contentDescription = stringResource(DashboardR.string.dashboard),
            onClick = component::onDashboardTabClicked
        )
        BottomTabItem(
            isSelected = activeComponent is TabChild.LiquidTab,
            painter = painterResource(LiquidR.drawable.ic_liquid),
            contentDescription = stringResource(LiquidR.string.input_liquid),
            onClick = component::onInputLiquidTabClicked
        )
        BottomTabItem(
            isSelected = activeComponent is TabChild.AchievementTab,
            painter = painterResource(AchievementR.drawable.ic_achievement),
            contentDescription = stringResource(AchievementR.string.achievement),
            onClick = component::onAchievementTabClicked
        )
        BottomTabItem(
            isSelected = activeComponent is TabChild.StatisticsTab,
            painter = painterResource(StatisticsR.drawable.ic_statistics),
            contentDescription = stringResource(StatisticsR.string.statistics),
            onClick = component::onStatisticsTabClicked
        )
    }
}

@Composable
@PreviewLightDark
private fun BottomBarPreview() {
    AirlyTheme {
        BottomBar(component = tabComponentPreview)
    }
}