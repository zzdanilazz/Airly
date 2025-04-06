@file:Suppress("unused")

package ru.health.featuredashboard.presentation

import dagger.Binds
import dagger.Module

@Module
internal interface DashboardPresentationModule {

    @Binds
    fun bindDashboardComponent(impl: DefaultDashboardComponent.Factory): DashboardComponent.Factory
}