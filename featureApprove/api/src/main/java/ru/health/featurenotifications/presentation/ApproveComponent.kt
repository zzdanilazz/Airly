package ru.health.featurenotifications.presentation

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.presentation.component.RenderComponent

@Immutable
interface ApproveComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onDismiss: () -> Unit
        ) : ApproveComponent
    }
}