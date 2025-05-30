@file:Suppress("unused")

package ru.health.featurenotifications.impl.presentation

import dagger.Binds
import dagger.Module
import ru.health.featurenotifications.api.presentation.ApproveComponent

@Module
internal interface ApprovePresentationModule {

    @Binds
    fun bindApproveComponent(impl: DefaultApproveComponent.Factory): ApproveComponent.Factory
}