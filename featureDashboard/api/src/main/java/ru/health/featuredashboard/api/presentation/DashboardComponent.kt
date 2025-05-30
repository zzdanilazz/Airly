package ru.health.featuredashboard.api.presentation

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.presentation.component.RenderComponent

@Immutable
interface DashboardComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onNotifications: () -> Unit,
            onUploadDetail: () -> Unit,
        ) : DashboardComponent
    }
}