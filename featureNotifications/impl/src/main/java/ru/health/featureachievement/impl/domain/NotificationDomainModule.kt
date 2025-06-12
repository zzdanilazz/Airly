@file:Suppress("unused")

package ru.health.featurenotifications.impl.domain

import dagger.Binds
import dagger.Module
import ru.health.featurenotifications.api.domain.SaveTokenUseCase

@Module
internal interface NotificationDomainModule {

    @Binds
    fun bindSaveTokenUseCase(impl: DefaultSaveTokenUseCase): SaveTokenUseCase
}