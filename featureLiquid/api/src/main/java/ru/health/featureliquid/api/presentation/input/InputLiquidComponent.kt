package ru.health.featureliquid.api.presentation.input

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.presentation.component.RenderComponent
import ru.health.featureliquid.api.domain.model.Device

@Immutable
interface InputLiquidComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            liquid: Device,
            onBack: () -> Unit
        ) : InputLiquidComponent
    }
}