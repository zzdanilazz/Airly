@file:Suppress("unused")

package ru.health.liquid.presentation

import dagger.Binds
import dagger.Module
import ru.health.liquid.presentation.detail.DefaultLiquidDetailComponent

@Module
internal interface LiquidPresentationModule {

    @Binds
    fun bindLiquidDetailComponent(impl: DefaultLiquidDetailComponent.Factory): LiquidDetailComponent.Factory
}