@file:Suppress("unused")

package ru.health.featurenotifications.presentation

import dagger.Binds
import dagger.Module

@Module
internal interface ApprovePresentationModule {

    @Binds
    fun bindApproveComponent(impl: DefaultApproveComponent.Factory): ApproveComponent.Factory
}