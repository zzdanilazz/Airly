@file:Suppress("unused")

package ru.health.featurestatistics

import dagger.Module
import ru.health.featurestatistics.data.StatisticsDataModule
import ru.health.featurestatistics.domain.StatisticsDomainModule
import ru.health.featurestatistics.presentation.StatisticsPresentationModule

@Module(includes = [StatisticsPresentationModule::class, StatisticsDataModule::class, StatisticsDomainModule::class])
interface StatisticsFeatureModule
