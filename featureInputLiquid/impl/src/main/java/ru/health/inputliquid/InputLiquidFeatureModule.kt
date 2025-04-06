@file:Suppress("unused")

package ru.health.inputliquid

import dagger.Module
import ru.health.inputliquid.data.InputLiquidDataModule
import ru.health.inputliquid.domain.InputLiquidDomainModule
import ru.health.inputliquid.presentation.InputLiquidPresentationModule

@Module(includes = [InputLiquidPresentationModule::class, InputLiquidDataModule::class, InputLiquidDomainModule::class])
interface InputLiquidFeatureModule
