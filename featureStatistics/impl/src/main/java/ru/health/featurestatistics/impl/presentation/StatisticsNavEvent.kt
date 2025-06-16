package ru.health.featurestatistics.impl.presentation

internal sealed interface StatisticsNavEvent {

    data class OnLiquidEdited(val editedVolume: Float) : StatisticsNavEvent

    data object Back : StatisticsNavEvent
}