package ru.health.featuredashboard.presentation.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.hazeSource
import ru.health.core.presentation.ui.gradient.GradientBox
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.core.presentation.ui.theme.LocalHazeState
import ru.health.featuredashboard.presentation.DashboardAction
import ru.health.featuredashboard.presentation.DashboardUiState
import ru.health.featuredashboard.presentation.ui.abstinence.AbstinencePeriodCard
import ru.health.featuredashboard.presentation.ui.airly.Airly
import ru.health.featuredashboard.presentation.ui.banner.BannerFeed
import ru.health.featuredashboard.presentation.ui.health.Health
import ru.health.featuredashboard.presentation.ui.saved_money.SavedMoneyCard

@Composable
internal fun Dashboard(
    modifier: Modifier = Modifier,
    state: DashboardUiState,
    onAction: (action: DashboardAction) -> Unit = {}
) {
    val hazeState = LocalHazeState.current

    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val listState = rememberLazyListState()

    val defaultContentPadding = 16.dp
    val contentModifier = Modifier.padding(horizontal = defaultContentPadding)

    GradientBox(
        modifier = Modifier
            .fillMaxSize()
            .hazeSource(LocalHazeState.current)
    )

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = defaultContentPadding)
    ) {
        item {
            DashboardTopBar(
                onUpload = { onAction(DashboardAction.OnUploadClick) },
                onNotifications = { onAction(DashboardAction.OnNotificationsClick) }
            )
            Health(
                modifier = contentModifier,
                value = state.health)
            Spacer(modifier = Modifier.height(18.dp))
            AbstinencePeriodCard(
                modifier = contentModifier,
                startAbstinenceTimeMillis = state.startAbstinenceTimeMillis
            )
        }
        stickyHeader {
            val isSticky by remember {
                derivedStateOf { listState.firstVisibleItemIndex > 0 }
            }
            val defaultSpacerHeight = 6.dp
            val spacerHeight by animateDpAsState(
                if (isSticky) 0.dp else defaultSpacerHeight
            )
            val cardPadding by animateDpAsState(
                if (isSticky) 0.dp else defaultContentPadding
            )
            val cardSpacerHeight by animateDpAsState(
                if (isSticky) statusBarHeight else defaultSpacerHeight
            )
            Spacer(modifier = Modifier.height(spacerHeight))
            SavedMoneyCard(
                modifier = modifier.padding(horizontal = cardPadding),
                hazeState = hazeState,
                value = state.savedMoney,
                topSpacerHeight = cardSpacerHeight
            )
        }
        item {
            Spacer(modifier = Modifier.fillParentMaxHeight(0.1f))
            Airly(
                modifier = Modifier.hazeSource(hazeState),
                health = state.health
            )
            Spacer(modifier = Modifier.fillParentMaxHeight(0.1f))
        }
        item {
            BannerFeed(
                modifier = Modifier.hazeSource(hazeState),
                maxPrice = state.savedMoney
            )
        }
    }
}

internal val dashboardUiStatePreview by lazy {
    DashboardUiState(
        hasNotifications = true,
        health = 86,
        savedMoney = 3_398.08f
    )
}

@PreviewLightDark
@Composable
private fun DashboardPreview() {
    AirlyTheme {
        Dashboard(state = dashboardUiStatePreview)
    }
}