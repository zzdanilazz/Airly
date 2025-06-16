package ru.health.featureachievement.api.presentation

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.presentation.component.RenderComponent

@Immutable
interface ApproveComponent : RenderComponent {

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            params: ApproveParams,
            approveTypeId: Int,
            onDismiss: () -> Unit,
            onApproveEvent: (approveTypeId: Int, approveEventType: ApproveEventType, approveValues: ApproveValues) -> Unit,
        ): ApproveComponent
    }
}