@file:Suppress("unused")

package ru.health.featurenotifications.presentation

import dagger.Binds
import dagger.Module

@Module
internal interface AchievementPresentationModule {

    @Binds
    fun bindAchievementComponent(impl: DefaultAchievementComponent.Factory): AchievementComponent.Factory
}