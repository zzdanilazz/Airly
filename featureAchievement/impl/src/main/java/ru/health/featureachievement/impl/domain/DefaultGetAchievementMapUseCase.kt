package ru.health.featureachievement.impl.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import ru.health.core.api.RequestError
import ru.health.core.api.ResultError
import ru.health.core.api.data.MetaDataStore
import ru.health.core.api.domain.result.RootResult
import ru.health.core.impl.domain.getDurationFlow
import ru.health.core.impl.domain.inDaysFloat
import ru.health.featureachievement.api.domain.AchievementRepository
import ru.health.featureachievement.api.domain.model.Achievement
import ru.health.featureachievement.api.domain.model.AchievementType
import ru.health.featureachievement.api.domain.usecase.GetAchievementMapUseCase
import ru.health.featuredashboard.api.domain.DashboardRepository
import ru.health.featuredashboard.api.domain.usecase.GetSavedMoneyFlowUseCase
import ru.health.featureliquid.api.domain.LiquidRepository
import javax.inject.Inject

class DefaultGetAchievementMapUseCase @Inject constructor(
    private val getSavedMoneyFlowUseCase: GetSavedMoneyFlowUseCase,
    private val achievementRepository: AchievementRepository,
    private val dashboardRepository: DashboardRepository,
    private val liquidRepository: LiquidRepository,
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

    private suspend fun mapBaseList(achievements: List<Achievement>): Flow<List<Achievement>> {
        val isProgressShared = metaDataStore.getIsProgressShared()
        val isConsumptionAdded = liquidRepository.isConsumptionAdded()
        val isMeasuredConsumptionAdded = liquidRepository.isMeasureConsumptionAdded()
        return flowOf(
            achievements.map {
                when (it.id) {
                    1 -> it.copy(value = if (isConsumptionAdded) 1 else 0)
                    2 -> it.copy(value = if (isProgressShared) 1 else 0)
                    3 -> it.copy(value = if (isMeasuredConsumptionAdded) 1 else 0)
                    else -> it
                }
            }
        )
    }

    private suspend fun mapAbstinenceDurationList(achievements: List<Achievement>): Flow<List<Achievement>> {
        val firstDate = liquidRepository.getFirstConsumptionDate()
        val daysFlow = firstDate?.let { date ->
            getDurationFlow(date).map { duration ->
                duration.inDaysFloat
            }
        } ?: emptyFlow()
        return daysFlow.map { days ->
            achievements.map { it.copy(value = days.toInt()) }
        }
    }

    private suspend fun mapSavedMoneyList(achievements: List<Achievement>): Flow<List<Achievement>> {
        val firstDate = liquidRepository.getFirstConsumptionDate()
        val daysFlow = firstDate?.let { date ->
            getDurationFlow(date)
        } ?: emptyFlow()
        return getSavedMoneyFlowUseCase(daysFlow).dataOrDefault(emptyFlow()).map { money ->
            achievements.map { it.copy(value = money.toInt()) }
        }
    }
}
