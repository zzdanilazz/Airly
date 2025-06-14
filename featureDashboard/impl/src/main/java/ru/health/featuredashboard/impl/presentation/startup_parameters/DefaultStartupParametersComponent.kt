package ru.health.featuredashboard.impl.presentation.startup_parameters

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.core.api.domain.Device
import ru.health.core.impl.presentation.EventEffect
import ru.health.featuredashboard.api.presentation.StartupParametersComponent
import ru.health.featuredashboard.impl.presentation.startup_parameters.ui.StartupParametersContent

internal class DefaultStartupParametersComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onApp: () -> Unit,
    @Assisted private val onInputLiquid: (liquid: Device) -> Unit,
    private val startupParametersViewModel: StartupParametersViewModel.Factory,
) : StartupParametersComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate { startupParametersViewModel() }

    override fun onLiquidEdited(editedVolume: Float) {
        viewModel.onAction(StartupParametersAction.OnLiquidEdited(editedVolume))
    }

    @Composable
    override fun Render(modifier: Modifier) {
        EventEffect(viewModel.navEvent) { event ->
            when (event) {
                StartupParametersNavEvent.OpenApp -> onApp()
                is StartupParametersNavEvent.OpenInputLiquid -> onInputLiquid(event.liquid)
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
            onInputLiquid: (liquid: Device) -> Unit
        ): DefaultStartupParametersComponent
    }
}
