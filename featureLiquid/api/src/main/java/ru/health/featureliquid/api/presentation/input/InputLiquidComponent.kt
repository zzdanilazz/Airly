package ru.health.featureliquid.api.presentation.input

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.presentation.component.RenderComponent
import ru.health.featureachievement.api.presentation.HandleApproveEvent
import ru.health.featureliquid.api.domain.model.FlaconParams

@Immutable
interface InputLiquidComponent : RenderComponent, HandleApproveEvent {
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