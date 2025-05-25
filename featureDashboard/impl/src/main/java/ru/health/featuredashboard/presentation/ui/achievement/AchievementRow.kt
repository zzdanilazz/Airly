package ru.health.featuredashboard.presentation.ui.achievement

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.featurenotifications.domain.Achievement
import ru.health.featuredashboard.presentation.ui.dashboardUiStatePreview

@Composable
internal fun AchievementRow(
    modifier: Modifier = Modifier,
    achievements: List<Achievement>,
    onAchievement: (Achievement) -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(130.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        achievements.forEach {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onAchievement(it) },
            ) {
                Image(
                    painter = painterResource(it.imageResId),
                    contentDescription = stringResource(it.nameResId)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AchievementRowPreview() {
    AirlyTheme {
        AchievementRow(achievements = dashboardUiStatePreview.achievements)
    }
}