package ru.health.featurestatistics.api.presentation

import androidx.compose.runtime.Immutable
import com.arkivanov.decompose.ComponentContext
import ru.health.core.api.presentation.component.RenderComponent

@Immutable
interface StatisticsComponent : RenderComponent {
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
        ) : StatisticsComponent
    }
}