package ru.health.featureliquid.impl.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.core.impl.presentation.EventEffect
import ru.health.core.api.domain.Device
import ru.health.featureliquid.api.presentation.detail.LiquidDetailComponent
import ru.health.featureliquid.impl.presentation.detail.ui.LiquidDetailContent

internal class DefaultLiquidDetailComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onInputLiquid: (liquid: Device) -> Unit,
    private val liquidDetailViewModel: LiquidDetailViewModel.Factory,
) : LiquidDetailComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate { liquidDetailViewModel() }

    @Composable
    override fun Render(modifier: Modifier) {
        EventEffect(viewModel.navEvent) { event ->
            when (event) {
                LiquidDetailNavEvent.AddAtomizer -> {}
                LiquidDetailNavEvent.AddLiquidBottle -> {}
                is LiquidDetailNavEvent.EditLiquidLevel -> onInputLiquid(event.liquid)
            }
        }

        LiquidDetailContent(
            modifier = modifier,
            viewModel = viewModel
        )
    }

    @AssistedFactory
    interface Factory : LiquidDetailComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onInputLiquid: (Device) -> Unit,
        ): DefaultLiquidDetailComponent
    }
}
