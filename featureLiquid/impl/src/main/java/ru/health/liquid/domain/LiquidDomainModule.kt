@file:Suppress("unused")
package ru.health.liquid.domain

import dagger.Binds
import dagger.Module
import ru.health.liquid.domain.usecase.GetVapeProductUseCase

@Module
internal interface LiquidDomainModule {

    @Binds
    fun bindGetLiquidInfoUseCase(impl: DefaultGetVapeProductUseCase): GetVapeProductUseCase

}