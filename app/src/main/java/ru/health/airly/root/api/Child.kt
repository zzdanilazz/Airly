package ru.health.airly.root.api

import ru.health.airly.tab.api.TabComponent
import ru.health.featuredashboard.api.presentation.StartupParametersComponent

sealed interface Child {

    class Tab(val component: TabComponent) : Child

    class StartupParameters(val component: StartupParametersComponent) : Child
}