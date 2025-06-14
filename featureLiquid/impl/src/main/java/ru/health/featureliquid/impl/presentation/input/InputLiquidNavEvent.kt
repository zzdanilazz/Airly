package ru.health.featureliquid.impl.presentation.input

internal sealed interface InputLiquidNavEvent {

    data class OnLiquidEdited(val editedVolume: Float) : InputLiquidNavEvent

    data object Back : InputLiquidNavEvent
}