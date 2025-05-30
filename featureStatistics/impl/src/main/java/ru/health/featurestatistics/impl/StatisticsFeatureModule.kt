@file:Suppress("unused")

package ru.health.featurestatistics.impl

import dagger.Module
import ru.health.featurestatistics.impl.data.StatisticsDataModule
import ru.health.featurestatistics.impl.domain.StatisticsDomainModule
import ru.health.featurestatistics.impl.presentation.StatisticsPresentationModule

@Module(includes = [StatisticsPresentationModule::class, StatisticsDataModule::class, StatisticsDomainModule::class])
interface StatisticsFeatureModule
