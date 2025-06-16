package ru.health.featureliquid.impl.presentation.input

internal sealed interface InputLiquidAction {

    data object Init : InputLiquidAction

    data class OnVolumeChange(val volume: Float) : InputLiquidAction

    data object OnSave : InputLiquidAction

    data object Back : InputLiquidAction
}