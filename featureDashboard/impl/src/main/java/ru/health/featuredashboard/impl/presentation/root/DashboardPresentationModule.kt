@file:Suppress("unused")

package ru.health.featuredashboard.impl.presentation.root

import dagger.Binds
import dagger.Module
import ru.health.featuredashboard.api.presentation.DashboardComponent

@Module
internal interface DashboardPresentationModule {

    @Binds
    fun bindDashboardComponent(impl: DefaultDashboardComponent.Factory): DashboardComponent.Factory
}