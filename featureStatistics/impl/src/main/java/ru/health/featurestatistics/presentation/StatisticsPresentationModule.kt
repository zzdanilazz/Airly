@file:Suppress("unused")

package ru.health.featurestatistics.presentation

import dagger.Binds
import dagger.Module

@Module
internal interface StatisticsPresentationModule {

    @Binds
    fun bindStatisticsComponent(impl: DefaultStatisticsComponent.Factory): StatisticsComponent.Factory
}