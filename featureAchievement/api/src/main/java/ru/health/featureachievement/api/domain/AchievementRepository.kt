package ru.health.featureachievement.api.domain

import ru.health.featureachievement.api.domain.model.Achievement
import ru.health.featureachievement.api.domain.model.AchievementType

interface AchievementRepository {

    suspend fun getAchievementMap(): Map<AchievementType, List<Achievement>>

}
