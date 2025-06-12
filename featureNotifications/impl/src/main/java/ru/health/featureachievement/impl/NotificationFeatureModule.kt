@file:Suppress("unused")

package ru.health.featurenotifications.impl

import dagger.Module
import ru.health.featurenotifications.impl.data.NotificationDataModule
import ru.health.featurenotifications.impl.domain.NotificationDomainModule
import ru.health.featurenotifications.impl.presentation.di.NotificationPresentationModule

@Module(
    includes = [
        NotificationDomainModule::class,
        NotificationPresentationModule::class,
        NotificationDataModule::class
    ]
)
interface NotificationFeatureModule
