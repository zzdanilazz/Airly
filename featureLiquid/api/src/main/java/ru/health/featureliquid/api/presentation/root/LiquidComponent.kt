package ru.health.featureliquid.api.presentation.root

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.presentation.component.bottom_bar.BottomBarRenderComponent
import ru.health.featureachievement.api.presentation.ApproveParams
import ru.health.featureachievement.api.presentation.HandleApproveEvent
import ru.health.featureliquid.api.domain.model.FlaconParams
import ru.health.featureliquid.api.presentation.input.EditLiquidCallback

@Immutable
interface LiquidComponent : BottomBarRenderComponent, EditLiquidCallback, HandleApproveEvent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onInputLiquid: (flaconParams: FlaconParams) -> Unit,
            onApprove: (approveParams: ApproveParams, approveTypeId: Int) -> Unit,
        ) : LiquidComponent
    }
}