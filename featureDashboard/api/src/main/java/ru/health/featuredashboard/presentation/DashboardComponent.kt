package ru.health.featuredashboard.presentation

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.presentation.component.RenderComponent
import ru.health.featurenotifications.domain.Achievement

@Immutable
interface DashboardComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onAchievementDetail: (achievement: Achievement) -> Unit,
            onNotifications: () -> Unit,
            onUploadDetail: () -> Unit,
        ) : DashboardComponent
    }
}