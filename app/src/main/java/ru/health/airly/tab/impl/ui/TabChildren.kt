package ru.health.airly.tab.impl.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import ru.health.airly.tab.api.TabChild
import ru.health.airly.tab.api.TabComponent
import ru.health.core.impl.presentation.ui.theme.AirlyTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun TabChildren(
    modifier: Modifier = Modifier,
    component: TabComponent,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val contentModifier = Modifier
        .fillMaxSize()
        .padding(contentPadding)

    Children(
        modifier = modifier,
        stack = component.stack,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is TabChild.DashboardTab -> child.component.Render(modifier = Modifier.fillMaxSize())
            is TabChild.LiquidTab -> child.component.Render(modifier = contentModifier)
            is TabChild.AchievementTab -> child.component.Render(modifier = contentModifier)
            is TabChild.StatisticsTab -> child.component.Render(modifier = contentModifier)
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@PreviewLightDark
private fun TabChildrenPreview() {
    AirlyTheme {
        TabChildren(component = tabComponentPreview)
    }
}