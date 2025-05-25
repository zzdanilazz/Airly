package ru.health.airly.tab.api

import ru.health.featurenotifications.presentation.AchievementComponent
import ru.health.featuredashboard.presentation.DashboardComponent
import ru.health.featurestatistics.presentation.StatisticsComponent
import ru.health.liquid.presentation.LiquidDetailComponent

sealed interface TabChild {

    class DashboardTab(val component: DashboardComponent) : TabChild

    class LiquidTab(val component: LiquidDetailComponent) : TabChild

    class AchievementTab(val component: AchievementComponent) : TabChild

    class StatisticsTab(val component: StatisticsComponent) : TabChild
}