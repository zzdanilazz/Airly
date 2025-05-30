@file:Suppress("unused")

package ru.health.featureliquid.impl.domain

import dagger.Binds
import dagger.Module
import ru.health.featureliquid.api.domain.usecase.GetVapeProductUseCase

@Module
internal interface LiquidDomainModule {

    @Binds
    fun bindGetLiquidInfoUseCase(impl: DefaultGetVapeProductUseCase): GetVapeProductUseCase

}