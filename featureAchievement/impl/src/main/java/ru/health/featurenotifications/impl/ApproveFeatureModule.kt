package ru.health.featurenotifications.impl

import dagger.Module
import ru.health.featurenotifications.impl.presentation.AchievementPresentationModule

@Module(includes = [AchievementPresentationModule::class])
interface AchievementFeatureModule
