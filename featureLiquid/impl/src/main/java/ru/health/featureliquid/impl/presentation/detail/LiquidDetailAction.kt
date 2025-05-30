package ru.health.featureliquid.impl.presentation.detail

import ru.health.featureliquid.api.domain.model.VapeProduct

internal sealed interface LiquidDetailAction {

    data object Init : LiquidDetailAction

    data class SwitchDeviceType(val isPodSelected: Boolean): LiquidDetailAction

    data object EditLiquidLevelApprove: LiquidDetailAction

    data class EditLiquidLevel(val liquid: VapeProduct.Liquid): LiquidDetailAction

    data object AddLiquidBottle: LiquidDetailAction

    data object AddAtomizer: LiquidDetailAction
}