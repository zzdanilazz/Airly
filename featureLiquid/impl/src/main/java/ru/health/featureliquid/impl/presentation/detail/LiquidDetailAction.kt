package ru.health.featureliquid.impl.presentation.detail

import ru.health.core.api.domain.DeviceType
import ru.health.featureliquid.api.domain.model.FlaconParams

internal sealed interface LiquidDetailAction {

    data object Init : LiquidDetailAction

    data class SwitchDeviceType(val deviceType: DeviceType): LiquidDetailAction

    data object EditLiquidLevelApprove: LiquidDetailAction

    data class EditLiquidLevel(val flaconParams: FlaconParams): LiquidDetailAction

    data object AddLiquidFlacon: LiquidDetailAction

    data object AddAtomizer: LiquidDetailAction

    data object AddConsumptionApprove: LiquidDetailAction

    data object AddDisposableApprove: LiquidDetailAction

}