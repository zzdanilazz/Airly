package ru.health.featurestatistics.impl.presentation

import ru.health.featurestatistics.impl.presentation.model.GraphType
import ru.health.featurestatistics.impl.presentation.model.Period

internal sealed interface StatisticsAction {

    data object Init : StatisticsAction

    data class ChangePeriod(val period: Period) : StatisticsAction

    data class ChangeGraphType(val graphType: GraphType) : StatisticsAction

    data object Back : StatisticsAction
}