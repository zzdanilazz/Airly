package ru.health.featuredashboard.presentation.ui.saved_money

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.presentation.ui.card.GlassmorphismCard
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.core.presentation.ui.theme.RubikOneFamily
import ru.health.featuredashboard.impl.R
import ru.health.featuredashboard.presentation.ui.dashboardUiStatePreview

@Composable
internal fun SavedMoneyCard(
    modifier: Modifier = Modifier,
    value: Float
) {
    GlassmorphismCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.saved_money_title).uppercase(),
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$value ₽",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = RubikOneFamily
            )
        }
    }
}

@Preview
@Composable
private fun SavedMoneyCardPreview() {
    AirlyTheme {
        SavedMoneyCard(value = dashboardUiStatePreview.savedMoney)
    }
}