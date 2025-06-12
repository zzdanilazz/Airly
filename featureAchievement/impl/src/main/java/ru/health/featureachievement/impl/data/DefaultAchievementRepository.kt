package ru.health.featureachievement.impl.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.health.featureachievement.api.data.AchievementLocalDataSource
import ru.health.featureachievement.api.domain.AchievementRepository
import ru.health.featureachievement.api.domain.model.Achievement
import ru.health.featureachievement.api.domain.model.AchievementType
import javax.inject.Inject

internal class DefaultAchievementRepository @Inject constructor(
    private val achievementLocalDataSource: AchievementLocalDataSource
) : AchievementRepository {

    override suspend fun getAchievementMap(): Map<AchievementType, List<Achievement>> =
        withContext(Dispatchers.IO) {
            achievementLocalDataSource.getAchievementMap()
        }
}