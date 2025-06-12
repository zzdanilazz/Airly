package ru.health.featurenotifications.impl.presentation.di

import ru.health.featurenotifications.impl.presentation.push.FirebasePushService

interface PushServiceInjectAssistant {

    fun inject(firebasePushService: FirebasePushService)

}
