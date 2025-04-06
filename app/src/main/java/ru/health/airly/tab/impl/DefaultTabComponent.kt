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
import ru.health.airly.tab.api.TabChild.Dashboard
import ru.health.airly.tab.api.TabChild.InputLiquid
import ru.health.airly.tab.api.TabChild.Statistics
import ru.health.airly.tab.api.TabComponent
import ru.health.airly.tab.impl.config.TabConfig
import ru.health.core.presentation.component.RootComponent
import ru.health.featuredashboard.presentation.DashboardComponent
import ru.health.featurestatistics.presentation.StatisticsComponent
import ru.health.inputliquid.presentation.InputLiquidComponent

internal class DefaultTabComponent @AssistedInject internal constructor(
    private val dashboardFactory: DashboardComponent.Factory,
    private val inputLiquidFactory: InputLiquidComponent.Factory,
    private val statisticsFactory: StatisticsComponent.Factory,
    @Assisted componentContext: ComponentContext
) : TabComponent, RootComponent<TabConfig, TabChild>(componentContext) {

    override val stack: Value<ChildStack<*, TabChild>> =
        childStack(
            source = navigation,
            initialConfiguration = TabConfig.Dashboard,
            serializer = TabConfig.serializer(),
            handleBackButton = true,
            childFactory = ::child,
        )

    override fun child(config: TabConfig, context: ComponentContext): TabChild = when (config) {
        is TabConfig.Dashboard -> Dashboard(dashboardComponent(context))
        is TabConfig.InputLiquid -> InputLiquid(inputLiquidComponent(context))
        is TabConfig.Statistics -> Statistics(statisticsComponent(context))
    }

    override fun onDashboardTabClicked() {
        navigation.bringToFront(TabConfig.Dashboard)
    }

    override fun onInputLiquidTabClicked() {
        navigation.bringToFront(TabConfig.InputLiquid)
    }

    override fun onStatisticsTabClicked() {
        navigation.bringToFront(TabConfig.Statistics)
    }

    private fun dashboardComponent(
        context: ComponentContext
    ): DashboardComponent = dashboardFactory(
        componentContext = context
    )

    private fun inputLiquidComponent(
        context: ComponentContext
    ): InputLiquidComponent = inputLiquidFactory(
        componentContext = context
    )

    private fun statisticsComponent(
        context: ComponentContext
    ): StatisticsComponent = statisticsFactory(
        componentContext = context
    )

    @AssistedFactory
    interface Factory : TabComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
        ): DefaultTabComponent
    }
}
