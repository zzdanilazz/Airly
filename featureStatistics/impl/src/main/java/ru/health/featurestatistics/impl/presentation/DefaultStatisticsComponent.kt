package ru.health.featurestatistics.impl.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.featurestatistics.api.presentation.StatisticsComponent
import ru.health.featurestatistics.impl.presentation.ui.StatisticsContent

internal class DefaultStatisticsComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    private val statisticsViewModel: StatisticsViewModel.Factory
) : StatisticsComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate {
        statisticsViewModel()
    }

    @Composable
    override fun Render(modifier: Modifier) {
        StatisticsContent(
            modifier = modifier,
            viewModel = viewModel
        )
    }

    @AssistedFactory
    interface Factory : StatisticsComponent.Factory {
        override fun invoke(
            @Assisted componentContext: ComponentContext,
        ): DefaultStatisticsComponent
    }
}
