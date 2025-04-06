package ru.health.airly.tab.impl.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import ru.health.airly.tab.api.TabChild
import ru.health.airly.tab.api.TabComponent
import ru.health.core.presentation.ui.theme.AirlyTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun TabChildren(
    modifier: Modifier = Modifier,
    component: TabComponent
) {
    Children(
        modifier = modifier,
        stack = component.stack,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is TabChild.Dashboard -> child.component.Render(modifier = Modifier.fillMaxSize())
            is TabChild.InputLiquid -> child.component.Render(modifier = Modifier.fillMaxSize())
            is TabChild.Statistics -> child.component.Render(modifier = Modifier.fillMaxSize())
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