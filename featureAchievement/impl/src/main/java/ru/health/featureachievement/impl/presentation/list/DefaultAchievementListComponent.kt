package ru.health.featureachievement.impl.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.core.impl.presentation.EventEffect
import ru.health.featureachievement.api.presentation.AchievementListComponent
import ru.health.featureachievement.impl.presentation.list.ui.AchievementListContent

internal class DefaultAchievementListComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    private val achievementListViewModel: AchievementListViewModel.Factory,
) : AchievementListComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate { achievementListViewModel() }

    @Composable
    override fun Render(modifier: Modifier) {
        EventEffect(viewModel.navEvent) { event ->
            when (event) {
                else -> {}
            }
        }

        AchievementListContent(
            modifier = modifier,
            viewModel = viewModel
        )
    }

    @AssistedFactory
    interface Factory : AchievementListComponent.Factory {
        override fun invoke(
            @Assisted componentContext: ComponentContext,
        ): DefaultAchievementListComponent
    }
}
