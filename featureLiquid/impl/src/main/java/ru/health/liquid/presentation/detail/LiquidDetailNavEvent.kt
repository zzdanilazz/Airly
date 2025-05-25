package ru.health.liquid.presentation.detail

internal sealed interface LiquidDetailNavEvent {

    data object EditLiquidLevel: LiquidDetailNavEvent

    data object AddLiquidBottle: LiquidDetailNavEvent

    data object AddAtomizer: LiquidDetailNavEvent
}