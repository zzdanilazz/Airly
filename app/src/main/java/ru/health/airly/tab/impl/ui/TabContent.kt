package ru.health.airly.tab.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import ru.health.airly.tab.api.TabChild
import ru.health.airly.tab.api.TabComponent
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.featuredashboard.presentation.DashboardComponent

@Composable
internal fun TabContent(
    modifier: Modifier = Modifier,
    component: TabComponent
) {
    Column(modifier = modifier) {
        TabChildren(
            modifier = Modifier.weight(1f),
            component = component
        )
        BottomBar(modifier = Modifier, component = component)
    }
}

private val dashboardComponentPreview = TabChild.Dashboard(
    object : DashboardComponent {
        @Composable
        override fun Render(modifier: Modifier) = Unit
    }
)

internal val tabComponentPreview = object : TabComponent {
    override val stack
        get() = MutableValue(
            ChildStack<Any, TabChild>(
                configuration = "configuration",
                instance = dashboardComponentPreview
            )
        )

    override fun onDashboardTabClicked() = Unit
    override fun onInputLiquidTabClicked() = Unit
    override fun onStatisticsTabClicked() = Unit
}

@Composable
@PreviewLightDark
private fun TabContentPreview() {
    AirlyTheme {
        TabContent(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            component = tabComponentPreview
        )
    }
}