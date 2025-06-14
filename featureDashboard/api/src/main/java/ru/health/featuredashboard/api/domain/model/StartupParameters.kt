package ru.health.featuredashboard.api.domain.model

import kotlinx.serialization.Serializable
import ru.health.core.api.domain.DeviceType

@Serializable
data class StartupParameters(
    val interests: Set<String>,
    val deviceType: DeviceType,
    val devicePrice: Int,
    val deviceBuyPeriod: Int,
    val vaporizerPrice: Int?,
    val vaporizerBuyPeriod: Int?
)
