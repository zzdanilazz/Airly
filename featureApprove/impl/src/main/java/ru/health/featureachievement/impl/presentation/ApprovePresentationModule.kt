@file:Suppress("unused")

package ru.health.featureachievement.impl.presentation

import dagger.Binds
import dagger.Module
import ru.health.featureachievement.api.presentation.ApproveComponent

@Module
internal interface ApprovePresentationModule {

    @Binds
    fun bindApproveComponent(impl: DefaultApproveComponent.Factory): ApproveComponent.Factory
}