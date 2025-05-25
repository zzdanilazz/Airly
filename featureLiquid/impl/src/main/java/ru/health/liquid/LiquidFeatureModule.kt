@file:Suppress("unused")

package ru.health.liquid

import dagger.Module
import ru.health.liquid.data.LiquidDataModule
import ru.health.liquid.domain.LiquidDomainModule
import ru.health.liquid.presentation.LiquidPresentationModule

@Module(includes = [LiquidPresentationModule::class, LiquidDataModule::class, LiquidDomainModule::class])
interface LiquidFeatureModule
