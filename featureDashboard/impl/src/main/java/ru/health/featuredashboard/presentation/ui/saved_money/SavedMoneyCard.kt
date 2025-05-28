package ru.health.featuredashboard.presentation.ui.saved_money

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.HazeState
import ru.health.core.presentation.ui.card.GlassmorphismCard
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.core.presentation.ui.theme.LocalHazeState
import ru.health.core.presentation.ui.theme.RubikOneFamily
import ru.health.featuredashboard.impl.R
import ru.health.featuredashboard.presentation.ui.dashboardUiStatePreview

@Composable
internal fun SavedMoneyCard(
    modifier: Modifier = Modifier,
    hazeState: HazeState = LocalHazeState.current,
    value: Float,
    topSpacerHeight: Dp = 0.dp,
    onClick: () -> Unit = {}
) {
    var iconImageVector by remember {
        mutableStateOf(Icons.Rounded.KeyboardArrowDown)
    }

    LaunchedEffect(topSpacerHeight) {
        iconImageVector = if (topSpacerHeight > 0.dp) {
            Icons.Rounded.KeyboardArrowUp
        } else Icons.Rounded.KeyboardArrowDown
    }

    GlassmorphismCard(
        modifier = modifier.clickable {
            onClick()
        },
        hazeState = hazeState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(topSpacerHeight))
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
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = iconImageVector,
                tint = Color.White,
                contentDescription = null
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