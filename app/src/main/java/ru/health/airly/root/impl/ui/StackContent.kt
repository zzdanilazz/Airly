package ru.health.airly.root.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.health.airly.root.api.Child

@Composable
internal fun StackContent(child: Child) {
    when (child) {
        is Child.Dashboard -> child.component.Render(Modifier)
    }
}