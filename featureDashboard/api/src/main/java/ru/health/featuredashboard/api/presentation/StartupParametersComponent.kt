package ru.health.featuredashboard.api.presentation

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.domain.Device
import ru.health.core.api.presentation.component.RenderComponent
import ru.health.featureliquid.api.presentation.input.EditLiquidCallback

@Immutable
interface StartupParametersComponent : RenderComponent, EditLiquidCallback {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onApp: () -> Unit,
            onInputLiquid: (liquid: Device) -> Unit
        ) : StartupParametersComponent
    }
}