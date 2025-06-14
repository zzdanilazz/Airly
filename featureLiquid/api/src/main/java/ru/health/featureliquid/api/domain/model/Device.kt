package ru.health.featureliquid.api.domain.model

import kotlinx.serialization.Serializable
import ru.health.core.api.domain.DeviceType

@Serializable
data class Device(
    val id: Int = 0,
    val deviceType: DeviceType,
    val date: String,
    val price: Int,
    val flaconParams: FlaconParams? = null,
    val consumptionFrequencies: List<ConsumptionFrequency> = emptyList(),
    val vapeActions: List<VapeAction> = emptyList()
)