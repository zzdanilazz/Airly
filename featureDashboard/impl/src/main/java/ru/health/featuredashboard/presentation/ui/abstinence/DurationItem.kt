package ru.health.featuredashboard.presentation.ui.abstinence

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.RubikOneFamily

@Composable
internal fun DurationItem(
    modifier: Modifier = Modifier,
    value: Int,
    name: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value.toString(),
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = RubikOneFamily
        )
        Text(
            text = name,
            color = Color.White,
            fontSize = 14.sp,
        )
    }
}

@Composable
@Preview
private fun DurationItemPreview() {
    AirlyTheme {
        DurationItem(
            value = 39,
            name = "секунд"
        )
    }
}