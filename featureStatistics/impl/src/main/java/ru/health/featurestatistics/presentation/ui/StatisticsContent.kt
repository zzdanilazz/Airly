package ru.health.featurestatistics.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.featurestatistics.presentation.ui.liquid.LiquidRow
import ru.health.featurestatistics.presentation.ui.money.MoneyCard

@Composable
internal fun StatisticsContent(
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp),
        state = lazyListState
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            LiquidRow()
            Spacer(modifier = Modifier.height(8.dp))
            MoneyCard(consumption = "1234,56")
        }
    }
}

@PreviewLightDark
@Composable
private fun StatisticsContentPreview() {
    AirlyTheme {
        StatisticsContent()
    }
}