package ru.health.airly.tab.api

import ru.health.featuredashboard.api.presentation.DashboardComponent
import ru.health.featureliquid.api.presentation.root.LiquidComponent
import ru.health.featureachievement.api.presentation.AchievementListComponent
import ru.health.featurestatistics.api.presentation.StatisticsComponent

sealed interface TabChild {

    val tabIndex: Int

    class DashboardTab(
        override val tabIndex: Int = 0,
        val component: DashboardComponent
    ) : TabChild

    class LiquidTab(
        override val tabIndex: Int = 1,
        val component: LiquidComponent
    ) : TabChild

    class AchievementTab(
        override val tabIndex: Int = 2,
        val component: AchievementListComponent
    ) : TabChild

    class StatisticsTab(
        override val tabIndex: Int = 3,
        val component: StatisticsComponent
    ) : TabChild
}