package ru.health.featurestatistics.impl.presentation.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ru.health.core.impl.presentation.ui._switch.SemitransparentSwitch
import ru.health.core.impl.presentation.ui.card.GlassmorphismCard
import ru.health.core.impl.presentation.ui.card.GlassmorphismCardShape
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.core.impl.presentation.ui.theme.RubikOneFamily
import ru.health.featurestatistics.impl.R
import ru.health.featurestatistics.impl.presentation.StatisticsAction
import ru.health.featurestatistics.impl.presentation.StatisticsUiState
import ru.health.featurestatistics.impl.presentation.model.GraphType
import ru.health.featurestatistics.impl.presentation.model.Period
import ru.health.core.impl.R as CoreR

@Composable
internal fun Statistics(
    modifier: Modifier = Modifier,
    state: StatisticsUiState,
    onAction: (action: StatisticsAction) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                SemitransparentSwitch(
                    items = GraphType.entries.map { it to it.iconRes },
                    selectedIndex = state.graphType.ordinal
                ) {
                    onAction(StatisticsAction.ChangeGraphType(it))
                }
            }

            val chartModifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)

            when (state.graphType) {
                GraphType.SAVED_MONEY -> {
                    state.lostMoneyData.takeIf { it.isNotEmpty() }?.let {
                        ColumnChart(
                            modifier = chartModifier,
                            data = remember { it },
                            barProperties = BarProperties(
                                cornerRadius = Bars.Data.Radius.Rectangle(
                                    topRight = 6.dp,
                                    topLeft = 6.dp
                                ),
                                spacing = 3.dp
                            ),
                            indicatorProperties = HorizontalIndicatorProperties(
                                textStyle = TextStyle.Default.copy(
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            ),
                            labelHelperProperties = LabelHelperProperties(
                                textStyle = TextStyle.Default.copy(
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            ),
                            labelProperties = LabelProperties(
                                enabled = false
                            )
                        )
                    }
                }

                GraphType.HEALTH -> {
                    state.lostFrequencyData.takeIf { it.isNotEmpty() }?.let {
                        LineChart(
                            modifier = chartModifier,
                            data = remember { it },
                            indicatorProperties = HorizontalIndicatorProperties(
                                textStyle = TextStyle.Default.copy(
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            ),
                            labelHelperProperties = LabelHelperProperties(
                                textStyle = TextStyle.Default.copy(
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            ),
                            labelProperties = LabelProperties(
                                enabled = false
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Period.entries.forEach {
                    val scale by animateFloatAsState(if (state.period == it) 1.1f else 0.9f)
                    GlassmorphismCard(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape = GlassmorphismCardShape)
                            .background(if (state.period == it) Color.White else Color.Transparent)
                            .clickable { onAction(StatisticsAction.ChangePeriod(it)) },
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = it.title,
                                fontFamily = RubikOneFamily,
                                color = if (state.period == it) LightRed else Color.White,
                                fontSize = 24.sp * scale
                            )
                        }
                    }
                }
            }
        }

        Column {
            val titleText: String
            val valueText: String

            when (state.graphType) {
                GraphType.SAVED_MONEY -> {
                    titleText = stringResource(R.string.money_lost)
                    valueText = stringResource(CoreR.string.roubles, state.savedMoneySum)
                }
                GraphType.HEALTH -> {
                    titleText = stringResource(R.string.frequency_lost)
                    valueText = stringResource(CoreR.string.days, state.avgFrequency)
                }
            }

            Text(
                text = titleText.uppercase(),
                color = Color.White,
                fontSize = 24.sp
            )
            Text(
                text = valueText,
                color = Color.White,
                fontFamily = RubikOneFamily,
                fontSize = 24.sp
            )
        }
    }
}

internal val statisticsUiStatePreview = StatisticsUiState(
    lostMoneyData = listOf(
        Bars(
            label = "1",
            values = listOf(
                Bars.Data(value = 10.0, color = Brush.verticalGradient(listOf(Color.Blue)))
            )
        ),
        Bars(
            label = "2",
            values = listOf(
                Bars.Data(value = 20.0, color = Brush.verticalGradient(listOf(Color.Blue)))
            )
        ),
    )
)

@PreviewLightDark
@Composable
private fun StatisticsPreview() {
    AirlyTheme {
        GradientBox(blurred = true)
        Statistics(
            state = statisticsUiStatePreview
        )
    }
}