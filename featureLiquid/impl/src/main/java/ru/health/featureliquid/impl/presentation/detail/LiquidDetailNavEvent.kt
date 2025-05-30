package ru.health.featureliquid.impl.presentation.detail

internal sealed interface LiquidDetailNavEvent {

    data object EditLiquidLevel: LiquidDetailNavEvent

    data object AddLiquidBottle: LiquidDetailNavEvent

    data object AddAtomizer: LiquidDetailNavEvent
}