package ru.health.featureachievement.impl.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.featureachievement.api.presentation.ApproveComponent
import ru.health.featureachievement.api.presentation.ApproveEventType
import ru.health.featureachievement.api.presentation.ApproveParams
import ru.health.featureachievement.api.presentation.ApproveValues
import ru.health.featureachievement.impl.presentation.ui.ApproveContent

internal class DefaultApproveComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val params: ApproveParams,
    @Assisted private val approveTypeId: Int,
    @Assisted private val onDismiss: () -> Unit,
    @Assisted private val onApproveEvent: (
        approveTypeId: Int,
        approveEventType: ApproveEventType,
        approveValues: ApproveValues
    ) -> Unit,
) : ApproveComponent, ComponentContext by componentContext {

    @Composable
    override fun Render(modifier: Modifier) {
        ApproveContent(
            modifier = modifier,
            params = params,
            approveTypeId = approveTypeId,
            onApproveEvent = onApproveEvent,
            onDismiss = onDismiss
        )
    }

    @AssistedFactory
    interface Factory : ApproveComponent.Factory {
        override fun invoke(
            @Assisted componentContext: ComponentContext,
            @Assisted params: ApproveParams,
            @Assisted approveTypeId: Int,
            @Assisted onDismiss: () -> Unit,
            @Assisted onApproveEvent: (
                approveTypeId: Int,
                approveEventType: ApproveEventType,
                approveValues: ApproveValues
            ) -> Unit,
        ): DefaultApproveComponent
    }
}
