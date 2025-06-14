package ru.health.featureliquid.api.presentation.detail

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.presentation.component.RenderComponent
import ru.health.core.api.domain.Device
import ru.health.core.api.domain.FlaconParams

@Immutable
interface LiquidDetailComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onInputLiquid: (flaconParams: FlaconParams) -> Unit
        ) : LiquidDetailComponent
    }
}