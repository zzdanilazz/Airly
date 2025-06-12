package ru.health.featureachievement.impl.presentation.list.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.impl.presentation.ui.card.GlassmorphismCard
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.featureachievement.api.domain.model.Achievement
import ru.health.featureachievement.api.domain.model.AchievementType
import kotlin.math.min

@Composable
internal fun AchievementCard(
    modifier: Modifier = Modifier,
    achievementType: AchievementType,
    achievement: Achievement,
    isHidden: Boolean = false
) {
    val targetValue = min(achievement.value, achievement.thresholdValue)

    GlassmorphismCard(modifier = modifier.size(190.dp)) {
        Box(
            modifier = modifier.blur(if (isHidden) 20.dp else 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 8.dp,
                        vertical = 12.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (achievementType == AchievementType.BASE) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = achievement.name,
                        color = Color.White,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Box {
                    Image(
                        modifier = Modifier.size(110.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(achievement.imageResId),
                        contentDescription = achievement.name
                    )
                    if (achievementType == AchievementType.SAVED_MONEY) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(y = 8.dp)
                                .background(
                                    color = LightRed,
                                    shape = RoundedCornerShape(50)
                                )
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                                text = "${achievement.thresholdValue} " + (achievementType.units ?: ""),
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
                Text(
                    text = "$targetValue/${achievement.thresholdValue} " + (achievementType.units ?: ""),
                    color = Color.White,
                    fontSize = 14.sp
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(8.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(4.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(achievement.progress)
                            .fillMaxHeight()
                            .padding(2.dp)
                            .background(
                                color = LightRed,
                                shape = RoundedCornerShape(2.dp)
                            )
                    )
                }
            }
            if (!achievement.isUnlocked) {
                Spacer(
                    modifier = Modifier
                        .alpha(0.7f)
                        .fillMaxSize()
                        .background(Color.DarkGray)
                )
            }
        }
    }
}

@Composable
@Preview
private fun AchievementCardBasePreview() {
    AirlyTheme {
        AchievementCard(
            achievement = baseAchievements[0],
            achievementType = AchievementType.BASE
        )
    }
}

@Composable
@Preview
private fun AchievementCardAbstinencePreview() {
    AirlyTheme {
        AchievementCard(
            achievement = abstinenceDurationAchievements[1],
            achievementType = AchievementType.ABSTINENCE_DURATION
        )
    }
}

@Composable
@Preview
private fun AchievementCardSavedMoneyPreview() {
    AirlyTheme {
        AchievementCard(
            achievement = savedMoneyAchievements[4],
            achievementType = AchievementType.SAVED_MONEY
        )
    }
}