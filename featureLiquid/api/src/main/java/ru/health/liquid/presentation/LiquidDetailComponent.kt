package ru.health.liquid.presentation

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.presentation.component.RenderComponent

@Immutable
interface LiquidDetailComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
        ) : LiquidDetailComponent
    }
}