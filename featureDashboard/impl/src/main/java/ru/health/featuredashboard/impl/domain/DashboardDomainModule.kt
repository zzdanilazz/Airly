@file:Suppress("unused")

package ru.health.featuredashboard.impl.domain

import dagger.Binds
import dagger.Module
import ru.health.featuredashboard.api.domain.usecase.GetDashboardInfoUseCase
import ru.health.featuredashboard.api.domain.usecase.GetIsStartupParametersSavedUseCase
import ru.health.featuredashboard.api.domain.usecase.SaveStartupParametersUseCase

@Module
internal interface DashboardDomainModule {

    @Binds
    fun bindGetDashboardInfoUseCase(useCase: DefaultGetDashboardInfoUseCase): GetDashboardInfoUseCase

    @Binds
    fun bindSaveStartupParametersUseCase(useCase: DefaultSaveStartupParametersUseCase): SaveStartupParametersUseCase

    @Binds
    fun bindGetIsStartupParametersSavedUseCase(useCase: DefaultGetIsStartupParametersSavedUseCase): GetIsStartupParametersSavedUseCase

}