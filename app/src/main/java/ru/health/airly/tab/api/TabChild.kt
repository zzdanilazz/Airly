package ru.health.airly.tab.api

import ru.health.featuredashboard.presentation.DashboardComponent
import ru.health.featurenotifications.presentation.AchievementComponent
import ru.health.featurestatistics.presentation.StatisticsComponent
import ru.health.liquid.presentation.LiquidDetailComponent

sealed interface TabChild {

    val tabIndex: Int

    class DashboardTab(
        override val tabIndex: Int = 0,
        val component: DashboardComponent
    ) : TabChild

    class LiquidTab(
        override val tabIndex: Int = 1,
        val component: LiquidDetailComponent
    ) : TabChild

    class AchievementTab(
        override val tabIndex: Int = 2,
        val component: AchievementComponent
    ) : TabChild

    class StatisticsTab(
        override val tabIndex: Int = 3,
        val component: StatisticsComponent
    ) : TabChild
}