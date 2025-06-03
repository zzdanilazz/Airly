package ru.health.featureliquid.api.presentation.detail

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.presentation.component.RenderComponent
import ru.health.featureliquid.api.domain.model.DeviceType

@Immutable
interface LiquidDetailComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onInputLiquid: (liquid: DeviceType.Liquid) -> Unit
        ) : LiquidDetailComponent
    }
}