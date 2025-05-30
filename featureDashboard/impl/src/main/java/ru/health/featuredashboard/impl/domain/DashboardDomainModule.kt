@file:Suppress("unused")

package ru.health.featuredashboard.impl.domain

import dagger.Binds
import dagger.Module
import ru.health.featuredashboard.api.domain.GetDashboardInfoUseCase

@Module
internal interface DashboardDomainModule {

    @Binds
    fun bindGetDashboardInfoUseCase(useCase: DefaultGetDashboardInfoUseCase): GetDashboardInfoUseCase
}