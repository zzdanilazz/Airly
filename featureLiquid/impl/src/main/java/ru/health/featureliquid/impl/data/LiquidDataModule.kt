@file:Suppress("unused")

package ru.health.featureliquid.impl.data

import dagger.Binds
import dagger.Module
import ru.health.featureliquid.api.data.LiquidLocalDataSource
import ru.health.featureliquid.api.domain.LiquidRepository
import javax.inject.Singleton

@Module
internal interface LiquidDataModule {

    @Binds
    @Singleton
    fun bindLiquidRepository(impl: DefaultLiquidRepository): LiquidRepository

    @Binds
    @Singleton
    fun bindLiquidLocalDataSource(impl: DefaultLiquidLocalDataSource): LiquidLocalDataSource

}