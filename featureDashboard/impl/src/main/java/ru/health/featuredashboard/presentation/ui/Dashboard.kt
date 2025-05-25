package ru.health.featuredashboard.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.featurenotifications.domain.Achievement
import ru.health.featuredashboard.impl.R
import ru.health.featuredashboard.presentation.DashboardAction
import ru.health.featuredashboard.presentation.DashboardUiState
import ru.health.featuredashboard.presentation.model.AbstinencePeriod
import ru.health.featuredashboard.presentation.ui.abstinence.AbstinencePeriodCard
import ru.health.featuredashboard.presentation.ui.achievement.AchievementRow
import ru.health.featuredashboard.presentation.ui.airly.Airly
import ru.health.featuredashboard.presentation.ui.health.Health
import ru.health.featuredashboard.presentation.ui.saved_money.SavedMoneyCard
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import ru.health.featureachievement.impl.R as AchievementR

@Composable
internal fun Dashboard(
    modifier: Modifier = Modifier,
    state: DashboardUiState,
    onAction: (action: DashboardAction) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 10.dp,
                bottom = 10.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Health(value = state.health)
        Column {
            AbstinencePeriodCard(abstinencePeriod = state.abstinencePeriod)
            Spacer(modifier = Modifier.height(6.dp))
            SavedMoneyCard(value = state.savedMoney)
        }
        AchievementRow(achievements = state.achievements) {
            onAction(DashboardAction.OnAchievementClick(it))
        }
        Airly(health = state.health)
    }
}

internal val dashboardUiStatePreview by lazy {
    DashboardUiState(
        hasNotifications = true,
        health = 86,
        abstinencePeriod = AbstinencePeriod(
            duration = durationPreview
        ),
        savedMoney = 3_398.08f,
        achievements = listOf(
            Achievement(
                id = 1,
                nameResId = R.string.saved_money_title,
                descriptionResId = R.string.saved_money_title,
                imageResId = AchievementR.drawable.illustration_one_day_abstinence
            ),
            Achievement(
                id = 2,
                nameResId = R.string.saved_money_title,
                descriptionResId = R.string.saved_money_title,
                imageResId = AchievementR.drawable.illustration_start_abstinence
            ),
            Achievement(
                id = 3,
                nameResId = R.string.saved_money_title,
                descriptionResId = R.string.saved_money_title,
                imageResId = AchievementR.drawable.illustration_one_thousand_money_saved
            )
        )
    )
}

private val durationPreview = 3.days + 12.hours + 48.minutes + 59.seconds

@PreviewLightDark
@Composable
private fun DashboardPreview() {
    AirlyTheme {
        Dashboard(state = dashboardUiStatePreview)
    }
}