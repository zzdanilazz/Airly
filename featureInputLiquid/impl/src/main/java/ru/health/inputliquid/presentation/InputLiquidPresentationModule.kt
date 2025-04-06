@file:Suppress("unused")

package ru.health.inputliquid.presentation

import dagger.Binds
import dagger.Module

@Module
internal interface InputLiquidPresentationModule {

    @Binds
    fun bindInputLiquidComponent(impl: DefaultInputLiquidComponent.Factory): InputLiquidComponent.Factory
}