package ru.health.inputliquid.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.inputliquid.presentation.ui.InputLiquidContent

internal class DefaultInputLiquidComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext
) : InputLiquidComponent, ComponentContext by componentContext {

    @Composable
    override fun Render(modifier: Modifier) {
        InputLiquidContent(
            modifier = modifier
        )
    }

    @AssistedFactory
    interface Factory : InputLiquidComponent.Factory {
        override fun invoke(
            @Assisted componentContext: ComponentContext,
        ): DefaultInputLiquidComponent
    }
}
