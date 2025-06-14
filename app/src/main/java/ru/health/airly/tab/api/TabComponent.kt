package ru.health.airly.tab.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.health.core.api.domain.Device
import ru.health.featureliquid.api.presentation.input.EditLiquidCallback

interface TabComponent: EditLiquidCallback {

    val stack: Value<ChildStack<*, TabChild>>

    fun onDashboardTabClicked()

    fun onLiquidTabClicked()

    fun onAchievementTabClicked()

    fun onStatisticsTabClicked()

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            onNotifications: () -> Unit,
            onUploadDetail: () -> Unit,
            onInputLiquid: (liquid: Device) -> Unit
        ): TabComponent
    }
}
