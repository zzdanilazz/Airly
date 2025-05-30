package ru.health.featurestatistics.impl.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.featurestatistics.api.presentation.StatisticsComponent
import ru.health.featurestatistics.impl.presentation.ui.StatisticsContent

internal class DefaultStatisticsComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext
) : StatisticsComponent, ComponentContext by componentContext {

    @Composable
    override fun Render(modifier: Modifier) {
        StatisticsContent(
            modifier = modifier
        )
    }

    @AssistedFactory
    interface Factory : StatisticsComponent.Factory {
        override fun invoke(
            @Assisted componentContext: ComponentContext,
        ): DefaultStatisticsComponent
    }
}
