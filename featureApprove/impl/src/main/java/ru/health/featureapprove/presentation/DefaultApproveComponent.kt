package ru.health.featureapprove.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.featureapprove.presentation.ui.ApproveContent

internal class DefaultApproveComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onDismiss: () -> Unit,
) : ApproveComponent, ComponentContext by componentContext {

    @Composable
    override fun Render(modifier: Modifier) {
        ApproveContent(
            modifier = modifier,
            onDismiss = onDismiss
        )
    }

    @AssistedFactory
    interface Factory : ApproveComponent.Factory {
        override fun invoke(
            @Assisted componentContext: ComponentContext,
            @Assisted onDismiss: () -> Unit,
        ): DefaultApproveComponent
    }
}
