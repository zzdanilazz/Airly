package ru.health.airly.root.api

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.health.core.api.presentation.component.RenderComponent

@Immutable
interface AppComponent : RenderComponent {

    val stack: Value<ChildStack<*, Child>>

    val slot: Value<ChildSlot<*, SlotChild>>

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): AppComponent
    }
}