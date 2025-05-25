package ru.health.featurestatistics.presentation.ui.liquid

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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

@Composable
internal fun RowScope.LiquidCard(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    @StringRes titleRes: Int,
    consumption: String
) {
    Card(
        modifier = modifier
            .fillMaxHeight()
            .weight(1f)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                modifier = Modifier.size(44.dp),
                painter = painterResource(id = iconRes),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = titleRes),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = consumption,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun LiquidCardPreview() {
    AirlyTheme {
        Row(
            modifier = Modifier.height(intrinsicSize = IntrinsicSize.Max)
        ) {
            LiquidCard(
                iconRes = R.drawable.ic_liquid,
                titleRes = R.string.liquid_consumption,
                consumption = "300 мл"
            )
        }
    }
}