package ru.health.core.api.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.value.Value

abstract class SlotRootComponent<Config : Any, Child : Any, SlotConfig : Any, SlotChild : Any>(
    componentContext: ComponentContext
) : RootComponent<Config, Child>(componentContext) {

    protected val slotNavigation = SlotNavigation<SlotConfig>()

    protected abstract val slot: Value<ChildSlot<*, SlotChild>>

    protected abstract fun slotChild(slotConfig: SlotConfig, context: ComponentContext): SlotChild

}