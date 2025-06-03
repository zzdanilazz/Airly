@file:Suppress("unused")

package ru.health.featureliquid.impl.presentation

import dagger.Binds
import dagger.Module
import ru.health.featureliquid.api.presentation.detail.LiquidDetailComponent
import ru.health.featureliquid.api.presentation.input.InputLiquidComponent
import ru.health.featureliquid.api.presentation.root.LiquidComponent
import ru.health.featureliquid.impl.presentation.detail.DefaultLiquidDetailComponent
import ru.health.featureliquid.impl.presentation.input.DefaultInputLiquidComponent
import ru.health.featureliquid.impl.presentation.root.DefaultLiquidComponent

@Module
internal interface LiquidPresentationModule {

    @Binds
    fun bindLiquidComponent(impl: DefaultLiquidComponent.Factory): LiquidComponent.Factory

    @Binds
    fun bindLiquidDetailComponent(impl: DefaultLiquidDetailComponent.Factory): LiquidDetailComponent.Factory

    @Binds
    fun bindInputLiquidComponent(impl: DefaultInputLiquidComponent.Factory): InputLiquidComponent.Factory
}