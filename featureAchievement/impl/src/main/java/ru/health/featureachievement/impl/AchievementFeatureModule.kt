package ru.health.featureachievement.impl

import dagger.Module
import ru.health.featureachievement.impl.data.AchievementDataModule
import ru.health.featureachievement.impl.domain.AchievementDomainModule
import ru.health.featureachievement.impl.presentation.AchievementPresentationModule

@Module(includes = [AchievementDomainModule::class, AchievementDataModule::class, AchievementPresentationModule::class])
interface AchievementFeatureModule
