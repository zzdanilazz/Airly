package ru.health.airly.root.api

import ru.health.airly.tab.api.TabComponent
import ru.health.featureachievement.api.presentation.HandleApproveEvent
import ru.health.featuredashboard.api.presentation.StartupParametersComponent
import ru.health.featureliquid.api.presentation.input.EditLiquidCallback
import ru.health.featureliquid.api.presentation.input.InputLiquidComponent

sealed interface Child: EditLiquidCallback, HandleApproveEvent {

    class Tab(val component: TabComponent) : Child,
        EditLiquidCallback by component, HandleApproveEvent by component

    class StartupParameters(val component: StartupParametersComponent) : Child,
        EditLiquidCallback by component

    class InputLiquid(val component: InputLiquidComponent) : Child,
        HandleApproveEvent by component
}