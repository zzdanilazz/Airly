@file:Suppress("unused")

package ru.health.featureachievement.impl.data

import dagger.Binds
import dagger.Module
import ru.health.featureachievement.api.data.AchievementLocalDataSource
import ru.health.featureachievement.api.domain.AchievementRepository

@Module
internal interface AchievementDataModule {

    @Binds
    fun bindAchievementRepository(impl: DefaultAchievementRepository): AchievementRepository

    @Binds
    fun bindAchievementLocalDataSource(impl: DefaultAchievementLocalDataSource): AchievementLocalDataSource
}