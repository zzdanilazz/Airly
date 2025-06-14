package ru.health.featuredashboard.impl.presentation.startup_parameters

import ru.health.core.api.domain.FlaconParams

internal sealed interface StartupParametersNavEvent {

    data object OpenApp : StartupParametersNavEvent

    data class OpenInputLiquid(val flaconParams: FlaconParams) : StartupParametersNavEvent
}