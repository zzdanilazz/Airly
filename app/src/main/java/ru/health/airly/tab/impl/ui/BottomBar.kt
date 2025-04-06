package ru.health.airly.tab.impl.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.health.airly.tab.api.TabChild
import ru.health.airly.tab.api.TabComponent
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.featuredashboard.impl.R as DashboardR
import ru.health.featureinputliquid.impl.R as InputLiquidR
import ru.health.featurestatistics.impl.R as StatisticsR

@Composable
internal fun BottomBar(modifier: Modifier = Modifier, component: TabComponent) {
    val childStack by component.stack.subscribeAsState()
    val activeComponent = childStack.active.instance

    NavigationBar(modifier = modifier, tonalElevation = 0.dp) {
        BottomTabItem(
            isSelected = activeComponent is TabChild.Dashboard,
            labelRes = DashboardR.string.dashboard,
            imageVector = Icons.Filled.Dashboard,
            onClick = component::onDashboardTabClicked
        )
        BottomTabItem(
            isSelected = activeComponent is TabChild.InputLiquid,
            labelRes = InputLiquidR.string.input_liquid,
            imageVector = Icons.Filled.WaterDrop,
            onClick = component::onInputLiquidTabClicked
        )
        BottomTabItem(
            isSelected = activeComponent is TabChild.Statistics,
            labelRes = StatisticsR.string.statistics,
            imageVector = Icons.Filled.BarChart,
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