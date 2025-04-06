package ru.health.airly.tab.api

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface TabComponent {

    val stack: Value<ChildStack<*, TabChild>>

    fun onDashboardTabClicked()

    fun onInputLiquidTabClicked()

    fun onStatisticsTabClicked()

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext
        ): TabComponent
    }
}
