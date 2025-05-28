package ru.health.airly.tab.impl.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeEffect
import ru.health.airly.tab.api.TabChild
import ru.health.airly.tab.api.TabComponent
import ru.health.core.presentation.ui.gradient.GradientBox
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.core.presentation.ui.theme.LightRed
import ru.health.core.presentation.ui.theme.LocalHazeState

@Composable
internal fun BottomBar(
    modifier: Modifier = Modifier,
    component: TabComponent
) {
    val childStack by component.stack.subscribeAsState()
    val activeComponent = childStack.active.instance

    val selectedTabIndex = activeComponent.tabIndex
    val tabs = BottomBarTabs.entries.toList()

    val brush = Brush.verticalGradient(
        listOf(
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 0.8f),
            Color.White.copy(alpha = 1f)
        ).map {
            val divider = if (isSystemInDarkTheme()) 5 else 1
            it.copy(alpha = it.alpha / divider)
        }
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
            .hazeEffect(
                state = LocalHazeState.current,
                style = HazeStyle.Unspecified
            )
            .background(brush)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                .defaultMinSize(minHeight = 80.dp)
        ) {
            val animatedSelectedTabIndex by animateFloatAsState(
                targetValue = selectedTabIndex.toFloat(),
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                    dampingRatio = Spring.DampingRatioLowBouncy,
                )
            )

            Canvas(
                modifier = Modifier
                    .matchParentSize()
                    .blur(
                        radius = 50.dp,
                        edgeTreatment = BlurredEdgeTreatment.Unbounded
                    )
            ) {
                val tabWidth = size.width / tabs.size
                drawCircle(
                    color = LightRed,
                    radius = size.height / 2,
                    center = Offset(
                        (tabWidth * animatedSelectedTabIndex) + tabWidth / 2,
                        size.height / 2
                    )
                )
            }

            Row {
                tabs.forEach { tab ->
                    val isSelected: Boolean
                    val onClick: () -> Unit
                    when (tab) {
                        BottomBarTabs.Dashboard -> {
                            isSelected = activeComponent is TabChild.DashboardTab
                            onClick = component::onDashboardTabClicked
                        }
                        BottomBarTabs.Liquid -> {
                            isSelected = activeComponent is TabChild.LiquidTab
                            onClick = component::onLiquidTabClicked
                        }
                        BottomBarTabs.Achievement -> {
                            isSelected = activeComponent is TabChild.AchievementTab
                            onClick = component::onAchievementTabClicked
                        }
                        BottomBarTabs.Statistics -> {
                            isSelected = activeComponent is TabChild.StatisticsTab
                            onClick = component::onStatisticsTabClicked
                        }
                    }
                    BottomTabItem(
                        isSelected = isSelected,
                        painter = painterResource(tab.icon),
                        contentDescription = stringResource(tab.title),
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun BottomBarPreview() {
    AirlyTheme {
        GradientBox {
            BottomBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                component = tabComponentPreview
            )
        }
    }
}