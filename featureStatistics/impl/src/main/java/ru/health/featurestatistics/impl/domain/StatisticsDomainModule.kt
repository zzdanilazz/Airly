@file:Suppress("unused")

package ru.health.featurestatistics.impl.domain

import dagger.Binds
import dagger.Module
import ru.health.featurestatistics.api.domain.GetAllDevicesUseCase

@Module
internal interface StatisticsDomainModule {

    @Binds
    fun bindGetAllDevicesUseCase(impl: DefaultGetAllDevicesUseCase): GetAllDevicesUseCase

}