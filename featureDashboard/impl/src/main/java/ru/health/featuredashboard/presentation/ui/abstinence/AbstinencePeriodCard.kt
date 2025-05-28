package ru.health.featuredashboard.presentation.ui.abstinence

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ru.health.core.presentation.ui.card.GlassmorphismCard
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.featuredashboard.impl.R
import ru.health.featuredashboard.presentation.model.AbstinencePeriod
import ru.health.featuredashboard.presentation.ui.dashboardUiStatePreview
import kotlin.time.Duration.Companion.milliseconds

@Composable
internal fun AbstinencePeriodCard(
    modifier: Modifier = Modifier,
    startAbstinenceTimeMillis: Long
) {
    var currentTime by remember {
        mutableLongStateOf(System.currentTimeMillis())
    }
    val duration = (currentTime - startAbstinenceTimeMillis).milliseconds
    val abstinencePeriod = AbstinencePeriod(duration)

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentTime = System.currentTimeMillis()
        }
    }

    GlassmorphismCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.abstinence_title).uppercase(),
                color = Color.White,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                abstinencePeriod.timeUnits.forEach { (value, name) ->
                    DurationItem(
                        value = value,
                        name = name
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun AbstinencePeriodCardPreview() {
    AirlyTheme {
        AbstinencePeriodCard(
            startAbstinenceTimeMillis = dashboardUiStatePreview.startAbstinenceTimeMillis
        )
    }
}