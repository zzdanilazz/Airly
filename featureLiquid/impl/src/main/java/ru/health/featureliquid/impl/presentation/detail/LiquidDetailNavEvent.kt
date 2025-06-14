package ru.health.featureliquid.impl.presentation.detail

import ru.health.featureliquid.api.domain.model.FlaconParams

internal sealed interface LiquidDetailNavEvent {

    data class EditLiquidLevel(val flaconParams: FlaconParams) : LiquidDetailNavEvent

    data object AddLiquidBottle: LiquidDetailNavEvent

    data object AddAtomizer: LiquidDetailNavEvent
}