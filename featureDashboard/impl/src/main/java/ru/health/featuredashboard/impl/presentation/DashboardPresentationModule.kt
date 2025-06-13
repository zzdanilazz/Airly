@file:Suppress("unused")

package ru.health.featuredashboard.impl.presentation

import dagger.Binds
import dagger.Module
import ru.health.featuredashboard.api.presentation.DashboardComponent
import ru.health.featuredashboard.api.presentation.StartupParametersComponent
import ru.health.featuredashboard.impl.presentation.dashboard.DefaultDashboardComponent
import ru.health.featuredashboard.impl.presentation.startup_parameters.DefaultStartupParametersComponent

@Module
internal interface DashboardPresentationModule {

    @Binds
    fun bindStartupParametersComponent(impl: DefaultStartupParametersComponent.Factory): StartupParametersComponent.Factory

    @Binds
    fun bindDashboardComponent(impl: DefaultDashboardComponent.Factory): DashboardComponent.Factory
}