package ru.health.airly.tab.api

import ru.health.featureachievement.api.presentation.AchievementListComponent
import ru.health.featureachievement.api.presentation.HandleApproveEvent
import ru.health.featuredashboard.api.presentation.DashboardComponent
import ru.health.featureliquid.api.presentation.input.EditLiquidCallback
import ru.health.featureliquid.api.presentation.root.LiquidComponent
import ru.health.featurestatistics.api.presentation.StatisticsComponent

sealed interface TabChild: EditLiquidCallback, HandleApproveEvent {

    val tabIndex: Int

    class DashboardTab(
        override val tabIndex: Int = 0,
        val component: DashboardComponent
    ) : TabChild, HandleApproveEvent by component

    class LiquidTab(
        override val tabIndex: Int = 1,
        val component: LiquidComponent
    ) : TabChild, EditLiquidCallback by component, HandleApproveEvent by component

    class AchievementTab(
        override val tabIndex: Int = 2,
        val component: AchievementListComponent
    ) : TabChild

    class StatisticsTab(
        override val tabIndex: Int = 3,
        val component: StatisticsComponent
    ) : TabChild
}