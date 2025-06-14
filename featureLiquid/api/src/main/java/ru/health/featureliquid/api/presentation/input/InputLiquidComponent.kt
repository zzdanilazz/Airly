package ru.health.featureliquid.api.presentation.input

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.domain.Device
import ru.health.core.api.presentation.component.RenderComponent

@Immutable
interface InputLiquidComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            liquid: Device,
            onEdited: (editedVolume: Float) -> Unit,
            onBack: () -> Unit,
        ) : InputLiquidComponent
    }
}