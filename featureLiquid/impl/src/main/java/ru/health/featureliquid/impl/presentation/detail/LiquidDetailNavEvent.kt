package ru.health.featureliquid.impl.presentation.detail

import ru.health.core.api.domain.DeviceType
import ru.health.featureliquid.api.domain.model.FlaconParams

internal sealed interface LiquidDetailNavEvent {

    data class EditLiquidLevel(val flaconParams: FlaconParams) : LiquidDetailNavEvent

    data object AddVaporizerApprove: LiquidDetailNavEvent

    data class AddPrimaryDevice(val deviceType: DeviceType) : LiquidDetailNavEvent

    data object AddConsumptionApprove: LiquidDetailNavEvent
}