package ru.health.featuredashboard.impl.presentation.startup_parameters

internal sealed interface StartupParametersNavEvent {

    data object OpenApp : StartupParametersNavEvent
}