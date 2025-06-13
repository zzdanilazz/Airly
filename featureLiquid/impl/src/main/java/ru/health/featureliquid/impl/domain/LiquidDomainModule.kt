@file:Suppress("unused")

package ru.health.featureliquid.impl.domain

import dagger.Binds
import dagger.Module
import ru.health.featureliquid.api.domain.usecase.GetDeviceUseCase

@Module
internal interface LiquidDomainModule {

    @Binds
    fun bindGetLiquidInfoUseCase(impl: DefaultGetDeviceUseCase): GetDeviceUseCase

}