package ru.health.featureliquid.impl.presentation.input

internal sealed interface InputLiquidAction {

    data object Init : InputLiquidAction

    data class OnVolumeChange(val volume: Float) : InputLiquidAction

    data object OnSaveApprove : InputLiquidAction

    data class OnSave(val volume: Int) : InputLiquidAction

    data object Back : InputLiquidAction
}