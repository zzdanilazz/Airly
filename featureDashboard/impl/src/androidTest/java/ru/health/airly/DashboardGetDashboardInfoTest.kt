package ru.health.airly

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.hasText
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atiurin.ultron.core.compose.createDefaultUltronComposeRule
import com.atiurin.ultron.extensions.assertIsDisplayed
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import ru.health.core.domain.result.RootResult
import ru.health.featuredashboard.domain.DashboardInfo
import ru.health.featuredashboard.domain.GetDashboardInfoUseCase
import ru.health.featuredashboard.presentation.DashboardAction
import ru.health.featuredashboard.presentation.DashboardViewModel
import ru.health.featuredashboard.presentation.ui.abstinence.AbstinencePeriodCard
import ru.health.featuredashboard.presentation.ui.achievement.AchievementRow
import ru.health.featuredashboard.presentation.ui.dashboardUiStatePreview
import ru.health.featuredashboard.presentation.ui.health.Health
import ru.health.featuredashboard.presentation.ui.saved_money.SavedMoneyCard

class DashboardGetDashboardInfoTest {
    @get:Rule
    val composeRule = createDefaultUltronComposeRule()

    @Test
    fun shouldUpdateTextAfterUserLoad() {
        composeRule.setContent {

            val mockUseCase = mockk<GetDashboardInfoUseCase>()
            val testData = with (dashboardUiStatePreview) {
                DashboardInfo(
                    hasNotifications = hasNotifications,
                    health = health,
                    startAbstinenceTimeMillis = abstinencePeriod.duration,
                    savedMoney = savedMoney
                )
            }

            coEvery { mockUseCase() } returns RootResult.Success(testData)

            val viewModel = DashboardViewModel(mockUseCase)
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.onAction(DashboardAction.Init)
            }
            Column {
                Health(value = state.health)
                AbstinencePeriodCard(startAbstinenceTimeMillis = state.abstinencePeriod)
                Spacer(modifier = Modifier.height(6.dp))
                SavedMoneyCard(value = state.savedMoney)
            }
            AchievementRow(achievements = state.achievements)
        }

        with (dashboardUiStatePreview) {
            hasText(abstinencePeriod.timeUnits[0].first.toString()).assertIsDisplayed()
            hasText(savedMoney.toString()).assertIsDisplayed()
            hasText(health.toString()).assertIsDisplayed()
        }
    }
}