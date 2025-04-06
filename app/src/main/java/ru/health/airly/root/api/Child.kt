package ru.health.airly.root.api

import ru.health.airly.tab.api.TabComponent

sealed interface Child {

    class Tab(val component: TabComponent) : Child
}