package ru.health.featureliquid.api.presentation.root

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.presentation.component.bottom_bar.BottomBarRenderComponent

@Immutable
interface LiquidComponent : BottomBarRenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
        ) : LiquidComponent
    }
}