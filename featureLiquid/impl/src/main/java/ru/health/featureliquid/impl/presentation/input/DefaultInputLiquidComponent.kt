package ru.health.featureliquid.impl.presentation.input

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.core.api.domain.FlaconParams
import ru.health.core.impl.presentation.EventEffect
import ru.health.featureliquid.api.presentation.input.InputLiquidComponent
import ru.health.featureliquid.impl.presentation.input.ui.InputLiquidContent

internal class DefaultInputLiquidComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted flaconParams: FlaconParams,
    @Assisted private val onEdited: (editedVolume: Float) -> Unit,
    @Assisted private val onBack: () -> Unit,
    private val inputLiquidViewModel: InputLiquidViewModel.Factory,
) : InputLiquidComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate { inputLiquidViewModel(flaconParams) }

    @Composable
    override fun Render(modifier: Modifier) {
        EventEffect(viewModel.navEvent) { event ->
            when (event) {
                InputLiquidNavEvent.Back -> onBack()
                is InputLiquidNavEvent.OnLiquidEdited -> onEdited(event.editedVolume)
            }
        }

        InputLiquidContent(
            modifier = modifier,
            viewModel = viewModel
        )
    }

    @AssistedFactory
    interface Factory : InputLiquidComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            flaconParams: FlaconParams,
            onEdited: (Float) -> Unit,
            onBack: () -> Unit
        ): DefaultInputLiquidComponent
    }
}
