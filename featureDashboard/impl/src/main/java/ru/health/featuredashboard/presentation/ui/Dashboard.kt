package ru.health.featuredashboard.presentation.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.health.core.presentation.ui.theme.AirlyTheme

@Composable
internal fun Dashboard(
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        state = lazyListState
    ) {

    }
}

@PreviewLightDark
@Composable
private fun DashboardPreview() {
    AirlyTheme {
        Dashboard()
    }
}