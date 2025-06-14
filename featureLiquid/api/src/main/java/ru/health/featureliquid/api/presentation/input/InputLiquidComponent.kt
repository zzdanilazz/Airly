package ru.health.featureliquid.api.presentation.input

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.domain.FlaconParams
import ru.health.core.api.presentation.component.RenderComponent

@Immutable
interface InputLiquidComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            flaconParams: FlaconParams,
            onEdited: (editedVolume: Float) -> Unit,
            onBack: () -> Unit,
        ) : InputLiquidComponent
    }
}