@file:Suppress("unused")

package ru.health.featureachievement.impl.presentation

import dagger.Binds
import dagger.Module
import ru.health.featureachievement.api.presentation.AchievementListComponent
import ru.health.featureachievement.impl.presentation.list.DefaultAchievementListComponent

@Module
internal interface AchievementPresentationModule {

    @Binds
    fun bindAchievementComponent(impl: DefaultAchievementListComponent.Factory): AchievementListComponent.Factory
}