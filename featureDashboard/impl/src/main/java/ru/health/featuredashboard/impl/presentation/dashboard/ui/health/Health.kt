package ru.health.featuredashboard.impl.presentation.dashboard.ui.health

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.core.impl.presentation.ui.theme.RubikOneFamily

@Composable
internal fun Health(
    modifier: Modifier = Modifier,
    value: Int
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = value.toString(),
                color = Color.White,
                fontFamily = RubikOneFamily,
                fontSize = 36.sp
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "+",
                color = LightRed,
                fontFamily = RubikOneFamily,
                fontSize = 48.sp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(28.dp)
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(14.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(value / 100f)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .background(
                        color = LightRed,
                        shape = RoundedCornerShape(10.dp)
                    )
            )
        }
    }
}

@Preview
@Composable
private fun HealthPreview() {
    AirlyTheme {
        Health(value = 86)
    }
}