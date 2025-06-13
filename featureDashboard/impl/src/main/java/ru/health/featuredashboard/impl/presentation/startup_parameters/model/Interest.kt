package ru.health.featuredashboard.impl.presentation.startup_parameters.model

import androidx.compose.runtime.Immutable

@Immutable
data class Interest(
    val name: String,
    val selected: Boolean = false
)