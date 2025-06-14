package ru.health.featureliquid.api.presentation.root

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.domain.Device
import ru.health.core.api.presentation.component.bottom_bar.BottomBarRenderComponent
import ru.health.featureliquid.api.presentation.input.EditLiquidCallback

@Immutable
interface LiquidComponent : BottomBarRenderComponent, EditLiquidCallback {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onInputLiquid: (Device) -> Unit,
        ) : LiquidComponent
    }
}