package ru.health.airly.root.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.health.airly.root.api.Child
import ru.health.airly.tab.impl.ui.TabScaffold
import ru.health.core.presentation.ui.gradient.GradientBox

@Composable
internal fun StackContent(child: Child) {
    GradientBox(modifier = Modifier.fillMaxSize()) {
        when (child) {
            is Child.Tab -> TabScaffold(
                modifier = Modifier.fillMaxSize(),
                component = child.component
            )
        }
    }
}