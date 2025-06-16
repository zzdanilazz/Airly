package ru.health.featurestatistics.impl.presentation

import androidx.compose.runtime.Immutable
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.Line
import ru.health.featurestatistics.impl.presentation.model.GraphType
import ru.health.featurestatistics.impl.presentation.model.Period

@Immutable
internal data class StatisticsUiState(
    val lostMoneyData: List<Bars> = emptyList(),
    val lostFrequencyData: List<Line> = emptyList(),
    val period: Period = Period.WEEK,
    val graphType: GraphType = GraphType.SAVED_MONEY
) {
    val savedMoneySum: Double
        get() = lostMoneyData.sumOf { it.values.sumOf { it.value } }

    val avgFrequency: Double
        get() = lostFrequencyData.sumOf { it.values.sumOf { it } } / lostFrequencyData.size

}