@file:Suppress("unused")

package ru.health.featurenotifications.impl.presentation.di

import android.app.NotificationChannel
import dagger.Binds
import dagger.Module
import ru.health.core.api.Factory
import ru.health.featurenotifications.api.presentation.NotificationListComponent
import ru.health.featurenotifications.api.presentation.NotificationProcess
import ru.health.featurenotifications.impl.presentation.DefaultNotificationListComponent
import ru.health.featurenotifications.impl.presentation.push.ChannelFactory
import ru.health.featurenotifications.impl.presentation.push.NotificationProcessImpl

@Module
internal interface NotificationPresentationModule {

    @Binds
    fun bindNotificationListComponent(impl: DefaultNotificationListComponent.Factory): NotificationListComponent.Factory

    @Binds
    fun bindNotificationProcess(impl: NotificationProcessImpl): NotificationProcess

    @Binds
    fun bindNotificationChannelFactory(impl: ChannelFactory): Factory<Boolean, NotificationChannel>
}