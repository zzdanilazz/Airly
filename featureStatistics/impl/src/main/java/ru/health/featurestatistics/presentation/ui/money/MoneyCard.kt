package ru.health.featurestatistics.presentation.ui.money

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.featurestatistics.impl.R
import ru.health.core.impl.R as CoreR

@Composable
internal fun MoneyCard(
    modifier: Modifier = Modifier,
    consumption: String
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                modifier = Modifier.size(44.dp),
                painter = painterResource(R.drawable.ic_money),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.money_consumption),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(CoreR.string.roubles, consumption),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun LiquidCardPreview() {
    AirlyTheme {
        MoneyCard(
            consumption = stringResource(CoreR.string.roubles, 1234)
        )
    }
}