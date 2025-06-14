package ru.health.featuredashboard.api.domain.model

import kotlinx.serialization.Serializable
import ru.health.featureliquid.api.domain.model.Device

@Serializable
data class StartupParameters(
    val interests: Set<String>,
    val primaryDevice: Device,
    val secondaryDevice: Device? = null,
    val pricePerPrimaryDevice: Int,
    val primaryDeviceBuyPeriod: Int,
    val pricePerSecondaryDevice: Int? = null,
    val secondaryDeviceBuyPeriod: Int? = null
)
