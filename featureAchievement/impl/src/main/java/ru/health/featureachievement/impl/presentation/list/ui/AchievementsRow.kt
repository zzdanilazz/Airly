package ru.health.featureachievement.impl.presentation.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.RubikOneFamily
import ru.health.featureachievement.api.domain.model.Achievement
import ru.health.featureachievement.api.domain.model.AchievementType

@Composable
internal fun AchievementsRow(
    modifier: Modifier = Modifier,
    achievementType: AchievementType,
    achievementList: List<Achievement>
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = achievementType.typeName,
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = RubikOneFamily
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(achievementList.size) { index ->
                val lastUnlockedIndex = achievementList.indexOfLast { it.isUnlocked }
                val isHidden = when {
                    achievementType == AchievementType.BASE -> false
                    index > 0 -> index - lastUnlockedIndex >= 2
                    else -> false
                }

                AchievementCard(
                    achievement = achievementList[index],
                    achievementType = achievementType,
                    isHidden = isHidden
                )
            }
        }
    }
}

@Composable
@Preview
private fun AchievementsRowPreview() {
    AirlyTheme {
        AchievementsRow(
            achievementType = AchievementType.ABSTINENCE_DURATION,
            achievementList = abstinenceDurationAchievements,
        )
    }
}