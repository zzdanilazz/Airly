package ru.health.airly.tab.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.health.featureachievement.api.domain.model.Achievement

interface TabComponent {

    val stack: Value<ChildStack<*, TabChild>>

    fun onDashboardTabClicked()

    fun onLiquidTabClicked()

    fun onAchievementTabClicked()

    fun onStatisticsTabClicked()

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onAchievementDetail: (achievement: Achievement) -> Unit,
            onNotifications: () -> Unit,
            onUploadDetail: () -> Unit,
        ): TabComponent
    }
}
