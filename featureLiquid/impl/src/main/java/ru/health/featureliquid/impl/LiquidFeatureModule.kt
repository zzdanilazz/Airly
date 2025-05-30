@file:Suppress("unused")

package ru.health.featureliquid.impl

import dagger.Module
import ru.health.featureliquid.impl.data.LiquidDataModule
import ru.health.featureliquid.impl.domain.LiquidDomainModule
import ru.health.featureliquid.impl.presentation.LiquidPresentationModule

@Module(includes = [LiquidPresentationModule::class, LiquidDataModule::class, LiquidDomainModule::class])
interface LiquidFeatureModule
