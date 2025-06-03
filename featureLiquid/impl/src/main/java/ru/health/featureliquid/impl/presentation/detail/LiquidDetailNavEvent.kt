package ru.health.featureliquid.impl.presentation.detail

import ru.health.featureliquid.api.domain.model.DeviceType

internal sealed interface LiquidDetailNavEvent {

    data class EditLiquidLevel(val liquid: DeviceType.Liquid) : LiquidDetailNavEvent

    data object AddLiquidBottle: LiquidDetailNavEvent

    data object AddAtomizer: LiquidDetailNavEvent
}