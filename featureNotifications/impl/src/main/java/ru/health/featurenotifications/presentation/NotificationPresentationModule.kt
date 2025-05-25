@file:Suppress("unused")

package ru.health.featurenotifications.presentation

import dagger.Binds
import dagger.Module

@Module
internal interface NotificationPresentationModule {

    @Binds
    fun bindNotificationListComponent(impl: DefaultNotificationListComponent.Factory): NotificationListComponent.Factory
}