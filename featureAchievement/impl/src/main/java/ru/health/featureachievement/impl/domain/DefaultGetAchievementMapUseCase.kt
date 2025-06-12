package ru.health.featureachievement.impl.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.data.MetaDataStore
import ru.health.core.api.domain.result.RootResult
import ru.health.core.impl.domain.getDurationFlow
import ru.health.featureachievement.api.domain.AchievementRepository
import ru.health.featureachievement.api.domain.model.Achievement
import ru.health.featureachievement.api.domain.model.AchievementType
import ru.health.featureachievement.api.domain.usecase.GetAchievementMapUseCase
import ru.health.featuredashboard.api.domain.DashboardRepository
import javax.inject.Inject

class DefaultGetAchievementMapUseCase @Inject constructor(
    private val achievementRepository: AchievementRepository,
    private val dashboardRepository: DashboardRepository,
    private val metaDataStore: MetaDataStore
) : GetAchievementMapUseCase {

    override suspend fun invoke(): RootResult<Map<AchievementType, Flow<List<Achievement>>>, ResultError> =
        try {
            val mapWithValues = achievementRepository.getAchievementMap().mapValues { (type, list) ->
                when (type) {
                    AchievementType.BASE -> mapBaseList(list)
                    AchievementType.ABSTINENCE_DURATION -> mapAbstinenceDurationList(list)
                    AchievementType.SAVED_MONEY -> mapSavedMoneyList(list)
                }
            }
            RootResult.Success(mapWithValues)
        } catch (_: Exception) {
            RootResult.Failure(RequestError.GENERIC)
        }

    private suspend fun mapBaseList(achievements: List<Achievement>): Flow<List<Achievement>> =
        flowOf(
            achievements.map {
                it.copy(value = metaDataStore.getAchievementValue(it.id).first())
            }
        )

    private suspend fun mapAbstinenceDurationList(achievements: List<Achievement>): Flow<List<Achievement>> {
        val daysFlow = getDurationFlow(dashboardRepository.getLastRelapsedDate()).map {
            it.inWholeDays
        }
        return daysFlow.map { days ->
            achievements.map { it.copy(value = days.toInt()) }
        }
    }

    private fun mapSavedMoneyList(achievements: List<Achievement>): Flow<List<Achievement>> =
        flowOf(
            achievements.map {
                it
            }
        )
}
