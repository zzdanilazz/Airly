@file:Suppress("unused")
package ru.health.featuredashboard.domain

import dagger.Binds
import dagger.Module

@Module
internal interface DashboardDomainModule {

    @Binds
    fun bindGetDashboardInfoUseCase(useCase: DefaultGetDashboardInfoUseCase): GetDashboardInfoUseCase
}