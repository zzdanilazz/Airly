package ru.health.featureliquid.impl.presentation.detail

import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.Device

internal sealed interface LiquidDetailAction {

    data object Init : LiquidDetailAction

    data class SwitchDeviceType(val deviceType: DeviceType): LiquidDetailAction

    data object EditLiquidLevelApprove: LiquidDetailAction

    data class EditLiquidLevel(val liquid: Device): LiquidDetailAction

    data object AddLiquidBottle: LiquidDetailAction

    data object AddAtomizer: LiquidDetailAction

    data object AddPuffsApprove: LiquidDetailAction

    data object AddDisposableApprove: LiquidDetailAction

}