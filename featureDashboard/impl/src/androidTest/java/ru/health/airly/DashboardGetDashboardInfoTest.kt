package ru.health.airly

import androidx.compose.ui.test.hasText
import com.atiurin.ultron.core.compose.createDefaultUltronComposeRule
import com.atiurin.ultron.extensions.assertIsDisplayed
import org.junit.Rule
import org.junit.Test
import ru.health.featuredashboard.impl.presentation.dashboard.ui.dashboardUiStatePreview

class DashboardGetDashboardInfoTest {
    @get:Rule
    val composeRule = createDefaultUltronComposeRule()

    @Test
    fun shouldUpdateTextAfterUserLoad() {
        composeRule.setContent {

//            val mockUseCase = mockk<GetDashboardInfoUseCase>()
//            val testData = with (dashboardUiStatePreview) {
//                DashboardInfo(
//                    hasNotifications = hasNotifications,
//                    health = health,
//                    abstinenceDuration = abstinencePeriod.duration,
//                    savedMoney = savedMoney
//                )
//            }
//
//            coEvery { mockUseCase() } returns RootResult.Success(testData)
//
//            val viewModel = DashboardViewModel(mockUseCase)
//            val state by viewModel.state.collectAsStateWithLifecycle()
//
//            LaunchedEffect(Unit) {
//                viewModel.onAction(DashboardAction.Init)
//            }
//            Column {
//                Health(value = state.health)
//                AbstinencePeriodCard(duration = state.abstinencePeriod)
//                Spacer(modifier = Modifier.height(6.dp))
//                SavedMoneyCard(value = state.savedMoney)
//            }
//            AchievementRow(achievements = state.achievements)
        }

        with (dashboardUiStatePreview) {
//            hasText(abstinencePeriod.timeUnits[0].first.toString()).assertIsDisplayed()
            hasText(savedMoney.toString()).assertIsDisplayed()
            hasText(health.toString()).assertIsDisplayed()
        }
    }
}