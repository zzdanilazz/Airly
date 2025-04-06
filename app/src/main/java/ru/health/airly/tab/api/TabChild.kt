package ru.health.airly.tab.api

import ru.health.featuredashboard.presentation.DashboardComponent
import ru.health.featurestatistics.presentation.StatisticsComponent
import ru.health.inputliquid.presentation.InputLiquidComponent

sealed interface TabChild {

    class Dashboard(val component: DashboardComponent) : TabChild

    class InputLiquid(val component: InputLiquidComponent) : TabChild

    class Statistics(val component: StatisticsComponent) : TabChild
}