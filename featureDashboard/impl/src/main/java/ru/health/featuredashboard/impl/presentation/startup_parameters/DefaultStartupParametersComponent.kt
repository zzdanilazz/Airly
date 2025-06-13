package ru.health.featuredashboard.impl.presentation.startup_parameters

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.core.impl.presentation.EventEffect
import ru.health.featuredashboard.api.presentation.StartupParametersComponent
import ru.health.featuredashboard.impl.presentation.startup_parameters.ui.StartupParametersContent

internal class DefaultStartupParametersComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onApp: () -> Unit,
    private val startupParametersViewModel: StartupParametersViewModel.Factory,
) : StartupParametersComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate { startupParametersViewModel() }

    @Composable
    override fun Render(modifier: Modifier) {
        EventEffect(viewModel.navEvent) { event ->
            when (event) {
                StartupParametersNavEvent.OpenApp -> onApp()
            }
        }

        StartupParametersContent(
            modifier = modifier,
            viewModel = viewModel
        )
    }

    @AssistedFactory
    interface Factory : StartupParametersComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onApp: () -> Unit,
        ): DefaultStartupParametersComponent
    }
}
