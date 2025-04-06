package ru.health.airly.root.api

import ru.health.featuredashboard.presentation.DashboardComponent

sealed interface Child {

    class Dashboard(val component: DashboardComponent) : Child
}