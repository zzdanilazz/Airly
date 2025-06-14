package ru.health.airly.tab.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.health.airly.tab.api.TabChild
import ru.health.airly.tab.api.TabChild.AchievementTab
import ru.health.airly.tab.api.TabChild.DashboardTab
import ru.health.airly.tab.api.TabChild.LiquidTab
import ru.health.airly.tab.api.TabChild.StatisticsTab
import ru.health.airly.tab.api.TabComponent
import ru.health.airly.tab.impl.config.TabConfig
import ru.health.core.api.presentation.component.RootComponent
import ru.health.featureachievement.api.presentation.AchievementListComponent
import ru.health.featuredashboard.api.presentation.DashboardComponent
import ru.health.featureliquid.api.domain.model.FlaconParams
import ru.health.featureliquid.api.presentation.root.LiquidComponent
import ru.health.featurestatistics.api.presentation.StatisticsComponent

internal class DefaultTabComponent @AssistedInject internal constructor(
    private val dashboardFactory: DashboardComponent.Factory,
    private val liquidFactory: LiquidComponent.Factory,
    private val achievementListFactory: AchievementListComponent.Factory,
    private val statisticsFactory: StatisticsComponent.Factory,
    @Assisted componentContext: ComponentContext,
    @Assisted(ON_NOTIFICATIONS) private val onNotifications: () -> Unit,
    @Assisted(ON_UPLOAD_DETAIL) private val onUploadDetail: () -> Unit,
    @Assisted(ON_INPUT_LIQUID) private val onInputLiquid: (flaconParams: FlaconParams) -> Unit,
) : TabComponent, RootComponent<TabConfig, TabChild>(componentContext) {

    override val stack: Value<ChildStack<*, TabChild>> =
        childStack(
            source = navigation,
            initialConfiguration = TabConfig.DashboardTab,
            serializer = TabConfig.serializer(),
            handleBackButton = true,
            childFactory = ::child,
        )

    override fun child(config: TabConfig, context: ComponentContext): TabChild = when (config) {
        TabConfig.DashboardTab -> DashboardTab(component = dashboardComponent(context))
        TabConfig.LiquidTab -> LiquidTab(component = liquidComponent(context))
        TabConfig.AchievementTab -> AchievementTab(component = achievementListComponent(context))
        TabConfig.StatisticsTab -> StatisticsTab(component = statisticsComponent(context))
    }

    override fun onDashboardTabClicked() {
        navigation.bringToFront(TabConfig.DashboardTab)
    }

    override fun onLiquidTabClicked() {
        navigation.bringToFront(TabConfig.LiquidTab)
    }

    override fun onAchievementTabClicked() {
        navigation.bringToFront(TabConfig.AchievementTab)
    }

    override fun onStatisticsTabClicked() {
        navigation.bringToFront(TabConfig.StatisticsTab)
    }

    private fun dashboardComponent(context: ComponentContext): DashboardComponent =
        dashboardFactory(
            componentContext = context,
            onNotifications = onNotifications,
            onUploadDetail = onUploadDetail,
        )

    private fun liquidComponent(context: ComponentContext): LiquidComponent =
        liquidFactory(
            componentContext = context,
            onInputLiquid = onInputLiquid
        )

    private fun achievementListComponent(context: ComponentContext): AchievementListComponent =
        achievementListFactory(
            componentContext = context
        )

    private fun statisticsComponent(context: ComponentContext): StatisticsComponent =
        statisticsFactory(
            componentContext = context
        )

    @AssistedFactory
    interface Factory : TabComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            @Assisted(ON_NOTIFICATIONS) onNotifications: () -> Unit,
            @Assisted(ON_UPLOAD_DETAIL) onUploadDetail: () -> Unit,
            @Assisted(ON_INPUT_LIQUID) onInputLiquid: (flaconParams: FlaconParams) -> Unit,
        ): DefaultTabComponent
    }

    companion object {
        private const val ON_NOTIFICATIONS = "ON_NOTIFICATIONS"
        private const val ON_UPLOAD_DETAIL = "ON_UPLOAD_DETAIL"
        private const val ON_INPUT_LIQUID = "ON_INPUT_LIQUID"
    }
}
