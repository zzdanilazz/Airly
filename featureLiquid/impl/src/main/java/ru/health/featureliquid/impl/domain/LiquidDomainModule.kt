@file:Suppress("unused")

package ru.health.featureliquid.impl.domain

import dagger.Binds
import dagger.Module
import ru.health.featureliquid.api.domain.usecase.AddDeviceUseCase
import ru.health.featureliquid.api.domain.usecase.EditLiquidUseCase
import ru.health.featureliquid.api.domain.usecase.GetDeviceUseCase
import ru.health.featureliquid.api.domain.usecase.SaveConsumptionUseCase

@Module
internal interface LiquidDomainModule {

    @Binds
    fun bindGetLiquidInfoUseCase(impl: DefaultGetDeviceUseCase): GetDeviceUseCase

    @Binds
    fun bindSaveLiquidInfoUseCase(impl: DefaultAddDeviceUseCase): AddDeviceUseCase

    @Binds
    fun bindSaveConsumptionUseCase(impl: DefaultSaveConsumptionUseCase): SaveConsumptionUseCase

    @Binds
    fun bindEditLiquidUseCase(impl: DefaultEditLiquidUseCase): EditLiquidUseCase

}