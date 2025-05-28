package ru.health.airly.tab.impl.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import ru.health.featureachievement.impl.R as AchievementR
import ru.health.featuredashboard.impl.R as DashboardR
import ru.health.featureliquid.impl.R as LiquidR
import ru.health.featurestatistics.impl.R as StatisticsR

enum class BottomBarTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val color: Color
) {
    Dashboard (
        title = DashboardR.string.dashboard,
        icon = DashboardR.drawable.ic_dashboard,
        color = Color(0xFFFFA574)
    ),
    Liquid(
        title = LiquidR.string.input_liquid,
        icon = LiquidR.drawable.ic_liquid,
        color = Color(0xFFFA6FFF)
    ),
    Achievement(
        title = AchievementR.string.achievement,
        icon = AchievementR.drawable.ic_achievement,
        color = Color(0xFFADFF64)
    ),
    Statistics(
        title = StatisticsR.string.statistics,
        icon = StatisticsR.drawable.ic_statistics,
        color = Color(0xFFFFA574)
    )
}