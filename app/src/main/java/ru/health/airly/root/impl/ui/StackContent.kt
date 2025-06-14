package ru.health.airly.root.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.health.airly.root.api.Child
import ru.health.airly.tab.impl.ui.TabScaffold

@Composable
internal fun StackContent(child: Child) {
    when (child) {
        is Child.Tab -> TabScaffold(
            modifier = Modifier.fillMaxSize(),
            component = child.component
        )

        is Child.StartupParameters -> child.component.Render(modifier = Modifier.fillMaxSize())
        is Child.InputLiquid -> child.component.Render(modifier = Modifier.fillMaxSize())
    }
}