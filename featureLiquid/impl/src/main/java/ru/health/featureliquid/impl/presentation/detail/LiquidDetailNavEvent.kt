package ru.health.featureliquid.impl.presentation.detail

import ru.health.featureliquid.api.domain.model.Device

internal sealed interface LiquidDetailNavEvent {

    data class EditLiquidLevel(val liquid: Device) : LiquidDetailNavEvent

    data object AddLiquidBottle: LiquidDetailNavEvent

    data object AddAtomizer: LiquidDetailNavEvent
}