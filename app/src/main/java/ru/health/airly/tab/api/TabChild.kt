package ru.health.airly.tab.api

import ru.health.featurenotifications.presentation.AchievementComponent
import ru.health.featuredashboard.presentation.DashboardComponent
import ru.health.featurestatistics.presentation.StatisticsComponent
import ru.health.inputliquid.presentation.InputLiquidComponent

sealed interface TabChild {

    class DashboardTab(val component: DashboardComponent) : TabChild

    class InputLiquidTab(val component: InputLiquidComponent) : TabChild

    class AchievementTab(val component: AchievementComponent) : TabChild

    class StatisticsTab(val component: StatisticsComponent) : TabChild
}