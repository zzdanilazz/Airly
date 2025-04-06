package ru.health.airly.root.impl.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.health.airly.root.api.Child
import ru.health.airly.tab.impl.ui.TabContent

@Composable
internal fun StackContent(child: Child) {
    when (child) {
        is Child.Tab -> TabContent(
            modifier = Modifier.fillMaxSize(),
            component = child.component
        )
    }
}