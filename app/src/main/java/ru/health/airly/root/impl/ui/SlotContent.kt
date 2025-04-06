package ru.health.airly.root.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.health.airly.root.api.SlotChild

@Composable
internal fun SlotContent(slot: SlotChild) {
    when (slot) {
        is SlotChild.Approve -> slot.component.Render(Modifier)
    }
}