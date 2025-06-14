package ru.health.featuredashboard.impl.presentation.startup_parameters

import ru.health.core.api.domain.Device

internal sealed interface StartupParametersNavEvent {

    data object OpenApp : StartupParametersNavEvent

    data class OpenInputLiquid(val liquid: Device) : StartupParametersNavEvent
}