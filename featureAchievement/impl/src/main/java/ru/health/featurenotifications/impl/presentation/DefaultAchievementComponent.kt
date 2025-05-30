package ru.health.featurenotifications.impl.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.featurenotifications.api.presentation.AchievementComponent
import ru.health.featurenotifications.impl.presentation.ui.AchievementContent

internal class DefaultAchievementComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext
) : AchievementComponent, ComponentContext by componentContext {

    @Composable
    override fun Render(modifier: Modifier) {
        AchievementContent(
            modifier = modifier
        )
    }

    @AssistedFactory
    interface Factory : AchievementComponent.Factory {
        override fun invoke(
            @Assisted componentContext: ComponentContext,
        ): DefaultAchievementComponent
    }
}
