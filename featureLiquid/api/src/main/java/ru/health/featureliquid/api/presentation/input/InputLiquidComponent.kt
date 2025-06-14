package ru.health.featureliquid.api.presentation.input

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.featureliquid.api.domain.model.FlaconParams
import ru.health.core.api.presentation.component.RenderComponent

@Immutable
interface InputLiquidComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            isPositiveVolume: Boolean,
            flaconParams: FlaconParams,
            onEdited: (editedVolume: Float) -> Unit,
            onBack: () -> Unit,
        ) : InputLiquidComponent
    }
}