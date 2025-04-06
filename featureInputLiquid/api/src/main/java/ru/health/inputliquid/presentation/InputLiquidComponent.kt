package ru.health.inputliquid.presentation

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.presentation.component.RenderComponent

@Immutable
interface InputLiquidComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
        ) : InputLiquidComponent
    }
}