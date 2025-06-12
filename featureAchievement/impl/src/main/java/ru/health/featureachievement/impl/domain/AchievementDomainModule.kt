@file:Suppress("unused")

package ru.health.featureachievement.impl.domain

import dagger.Binds
import dagger.Module
import ru.health.featureachievement.api.domain.usecase.GetAchievementMapUseCase

@Module
internal interface AchievementDomainModule {

    @Binds
    fun bindGetAchievementMapUseCase(impl: DefaultGetAchievementMapUseCase): GetAchievementMapUseCase

}