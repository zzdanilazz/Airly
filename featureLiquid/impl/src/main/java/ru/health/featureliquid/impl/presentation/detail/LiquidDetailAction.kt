package ru.health.featureliquid.impl.presentation.detail

import ru.health.core.api.domain.DeviceType

internal sealed interface LiquidDetailAction {

    data class SwitchDeviceType(val deviceType: DeviceType): LiquidDetailAction

    data object EditLiquidLevel: LiquidDetailAction

    data object AddVaporizerApprove: LiquidDetailAction

    data object AddConsumptionApprove: LiquidDetailAction

    data class AddConsumption(val vapeDurationDays: Float): LiquidDetailAction

    data class AddPrimaryDeviceApprove(val deviceType: DeviceType): LiquidDetailAction

    data class OnLiquidEdited(val editedVolume: Float): LiquidDetailAction

    data class AddFlacon(val price: String, val switchIndex: Int): LiquidDetailAction

    data class AddVaporizer(val price: Int): LiquidDetailAction

    data class AddDisposable(val price: Int): LiquidDetailAction

}