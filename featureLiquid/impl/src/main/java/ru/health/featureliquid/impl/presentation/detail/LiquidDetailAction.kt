package ru.health.featureliquid.impl.presentation.detail

import ru.health.featureliquid.api.domain.model.DeviceType

internal sealed interface LiquidDetailAction {

    data object Init : LiquidDetailAction

    data class SwitchDeviceType(val isPodSelected: Boolean): LiquidDetailAction

    data object EditLiquidLevelApprove: LiquidDetailAction

    data class EditLiquidLevel(val liquid: DeviceType.Liquid): LiquidDetailAction

    data object AddLiquidBottle: LiquidDetailAction

    data object AddAtomizer: LiquidDetailAction

    data object AddPuffsApprove: LiquidDetailAction

    data object AddDisposableApprove: LiquidDetailAction

}