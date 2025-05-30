@file:Suppress("unused")

package ru.health.featurenotifications.impl.presentation

import dagger.Binds
import dagger.Module
import ru.health.featurenotifications.api.presentation.AchievementComponent

@Module
internal interface AchievementPresentationModule {

    @Binds
    fun bindAchievementComponent(impl: DefaultAchievementComponent.Factory): AchievementComponent.Factory
}