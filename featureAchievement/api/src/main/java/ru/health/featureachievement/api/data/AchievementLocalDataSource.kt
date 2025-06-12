package ru.health.featureachievement.api.data

import ru.health.featureachievement.api.domain.model.Achievement
import ru.health.featureachievement.api.domain.model.AchievementType

interface AchievementLocalDataSource {

    suspend fun getAchievementMap(): Map<AchievementType, List<Achievement>>
}
