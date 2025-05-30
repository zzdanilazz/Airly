package ru.health.featurenotifications.api.presentation

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.presentation.component.RenderComponent

@Immutable
interface AchievementComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext
        ) : AchievementComponent
    }
}