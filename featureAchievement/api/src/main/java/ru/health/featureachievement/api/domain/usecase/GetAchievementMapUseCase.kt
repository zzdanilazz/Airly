package ru.health.featureachievement.api.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.health.core.api.ResultError
import ru.health.core.api.domain.result.RootResult
import ru.health.featureachievement.api.domain.model.Achievement
import ru.health.featureachievement.api.domain.model.AchievementType

interface GetAchievementMapUseCase {

    suspend operator fun invoke(): RootResult<Map<AchievementType, Flow<List<Achievement>>>, ResultError>

}