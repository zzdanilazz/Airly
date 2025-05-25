package ru.health.featurenotifications.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.featurenotifications.presentation.ui.NotificationListContent

internal class DefaultNotificationListComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onDismiss: () -> Unit,
) : NotificationListComponent, ComponentContext by componentContext {

    @Composable
    override fun Render(modifier: Modifier) {
        NotificationListContent(
            modifier = modifier,
            onDismiss = onDismiss
        )
    }

    @AssistedFactory
    interface Factory : NotificationListComponent.Factory {
        override fun invoke(
            @Assisted componentContext: ComponentContext,
            @Assisted onDismiss: () -> Unit,
        ): DefaultNotificationListComponent
    }
}
