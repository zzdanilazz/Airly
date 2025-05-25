package ru.health.liquid.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.liquid.presentation.LiquidDetailComponent
import ru.health.liquid.presentation.detail.ui.LiquidDetailContent

internal class DefaultLiquidDetailComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    private val liquidDetailViewModel: LiquidDetailViewModel.Factory,
) : LiquidDetailComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate { liquidDetailViewModel() }

    @Composable
    override fun Render(modifier: Modifier) {
        LiquidDetailContent(
            modifier = modifier,
            viewModel = viewModel
        )
    }

    @AssistedFactory
    interface Factory : LiquidDetailComponent.Factory {
        override fun invoke(
            @Assisted componentContext: ComponentContext,
        ): DefaultLiquidDetailComponent
    }
}
