package ru.health.featuredashboard.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.featuredashboard.presentation.ui.DashboardContent

internal class DefaultDashboardComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext
) : DashboardComponent, ComponentContext by componentContext {

    @Composable
    override fun Render(modifier: Modifier) {
        DashboardContent(
            modifier = modifier
        )
    }

    @AssistedFactory
    interface Factory : DashboardComponent.Factory {
        override fun invoke(
            @Assisted componentContext: ComponentContext,
        ): DefaultDashboardComponent
    }
}
