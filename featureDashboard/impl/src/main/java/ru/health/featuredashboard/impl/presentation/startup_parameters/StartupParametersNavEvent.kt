package ru.health.featuredashboard.impl.presentation.startup_parameters

import ru.health.featureliquid.api.domain.model.FlaconParams

internal sealed interface StartupParametersNavEvent {

    data object OpenApp : StartupParametersNavEvent

    data class OpenInputLiquid(val flaconParams: FlaconParams) : StartupParametersNavEvent
}