package ru.health.featureachievement.impl.presentation.list

import androidx.compose.runtime.Immutable
import ru.health.featureachievement.api.domain.model.AchievementType
import ru.health.featureachievement.api.domain.model.Achievement

@Immutable
internal data class AchievementListUiState(
    val achievementMap: Map<AchievementType, List<Achievement>> = emptyMap()
)