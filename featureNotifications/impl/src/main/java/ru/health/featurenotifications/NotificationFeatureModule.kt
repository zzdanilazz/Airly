@file:Suppress("unused")

package ru.health.featurenotifications

import dagger.Module
import ru.health.featurenotifications.presentation.NotificationPresentationModule

@Module(includes = [NotificationPresentationModule::class])
interface NotificationFeatureModule
