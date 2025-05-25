@file:Suppress("unused")

package ru.health.featurenotifications

import dagger.Module
import ru.health.featurenotifications.presentation.AchievementPresentationModule

@Module(includes = [AchievementPresentationModule::class])
interface AchievementFeatureModule
