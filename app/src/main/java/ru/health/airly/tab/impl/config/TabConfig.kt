package ru.health.airly.tab.impl.config

import kotlinx.serialization.Serializable

@Serializable
sealed interface TabConfig {

    @Serializable
    data object DashboardTab : TabConfig

    @Serializable
    data object InputLiquidTab : TabConfig

    @Serializable
    data object AchievementTab : TabConfig

    @Serializable
    data object StatisticsTab : TabConfig
}