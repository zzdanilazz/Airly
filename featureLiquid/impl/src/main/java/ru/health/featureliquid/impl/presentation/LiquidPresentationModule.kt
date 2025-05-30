@file:Suppress("unused")

package ru.health.featureliquid.impl.presentation

import dagger.Binds
import dagger.Module
import ru.health.featureliquid.api.presentation.LiquidDetailComponent
import ru.health.featureliquid.impl.presentation.detail.DefaultLiquidDetailComponent

@Module
internal interface LiquidPresentationModule {

    @Binds
    fun bindLiquidDetailComponent(impl: DefaultLiquidDetailComponent.Factory): LiquidDetailComponent.Factory
}