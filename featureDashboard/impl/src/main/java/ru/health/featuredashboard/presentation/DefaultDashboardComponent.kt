package ru.health.featuredashboard.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.core.presentation.EventEffect
import ru.health.featurenotifications.domain.Achievement
import ru.health.featuredashboard.presentation.ui.DashboardContent

internal class DefaultDashboardComponent @AssistedInject internal constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted(ON_ACHIEVEMENT_DETAIL) private val onAchievementDetail: (achievement: Achievement) -> Unit,
    @Assisted(ON_NOTIFICATIONS) private val onNotifications: () -> Unit,
    @Assisted(ON_UPLOAD_DETAIL) private val onUploadDetail: () -> Unit,
    private val dashboardViewModel: DashboardViewModel.Factory,
) : DashboardComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate { dashboardViewModel() }

    @Composable
    override fun Render(modifier: Modifier) {
        EventEffect(viewModel.navEvent) { event ->
            when (event) {
                is DashboardNavEvent.OpenAchievementDetail -> onAchievementDetail(event.achievement)
                DashboardNavEvent.OpenNotifications -> onNotifications()
                DashboardNavEvent.OpenUploadDetail -> onUploadDetail()
            }
        }

        DashboardContent(
            modifier = modifier,
            viewModel = viewModel
        )
    }

    @AssistedFactory
    interface Factory : DashboardComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            @Assisted(ON_ACHIEVEMENT_DETAIL) onAchievementDetail: (achievement: Achievement) -> Unit,
            @Assisted(ON_NOTIFICATIONS) onNotifications: () -> Unit,
            @Assisted(ON_UPLOAD_DETAIL) onUploadDetail: () -> Unit,
        ): DefaultDashboardComponent
    }

    companion object {
        private const val ON_ACHIEVEMENT_DETAIL = "ON_ACHIEVEMENT_DETAIL"
        private const val ON_NOTIFICATIONS = "ON_NOTIFICATIONS"
        private const val ON_UPLOAD_DETAIL = "ON_UPLOAD_DETAIL"
    }
}
