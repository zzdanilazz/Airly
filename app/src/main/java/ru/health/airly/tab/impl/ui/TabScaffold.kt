package ru.health.airly.tab.impl.ui

import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import ru.health.airly.tab.api.TabChild
import ru.health.airly.tab.api.TabComponent
import ru.health.core.api.presentation.component.bottom_bar.rememberBottomBarVisibility
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featuredashboard.api.presentation.DashboardComponent

@Composable
internal fun TabScaffold(
    modifier: Modifier = Modifier,
    component: TabComponent
) {
    val bottomBarVisibility = rememberBottomBarVisibility()
    val isBottomBarVisible = bottomBarVisibility.bottomBar.isVisible

    Scaffold(
        modifier = modifier,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.only(
            sides = WindowInsetsSides.Horizontal
        ),
        containerColor = Color.Transparent,
        bottomBar = {
            BottomBar(
                component = component,
                isVisible = isBottomBarVisible
            )
        },
    ) { padding ->
        TabChildren(
            modifier = Modifier.fillMaxSize(),
            contentPadding = padding,
            component = component,
            bottomBarVisibility = bottomBarVisibility
        )
    }
}

private val dashboardTabComponentPreview = TabChild.DashboardTab(
    component = object : DashboardComponent {
        @Composable
        override fun Render(modifier: Modifier) = Unit
    }
)

internal val tabComponentPreview = object : TabComponent {
    override val stack
        get() = MutableValue(
            ChildStack<Any, TabChild>(
                configuration = "configuration",
                instance = dashboardTabComponentPreview
            )
        )

    override fun onDashboardTabClicked() = Unit
    override fun onLiquidTabClicked() = Unit
    override fun onAchievementTabClicked() = Unit
    override fun onStatisticsTabClicked() = Unit
}

@Composable
@PreviewLightDark
private fun TabContentPreview() {
    AirlyTheme {
        TabScaffold(component = tabComponentPreview)
    }
}