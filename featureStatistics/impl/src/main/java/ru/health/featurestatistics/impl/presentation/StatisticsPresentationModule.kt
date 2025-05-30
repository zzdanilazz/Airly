@file:Suppress("unused")

package ru.health.featurestatistics.impl.presentation

import dagger.Binds
import dagger.Module
import ru.health.featurestatistics.api.presentation.StatisticsComponent

@Module
internal interface StatisticsPresentationModule {

    @Binds
    fun bindStatisticsComponent(impl: DefaultStatisticsComponent.Factory): StatisticsComponent.Factory
}