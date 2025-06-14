package ru.health.featureliquid.api.data

import java.util.Date

data class DeviceData(
    val id: Int = 0,
    val deviceTypeId: Int,
    val isPrimary: Boolean,
    val date: Date,
    val price: Int,
    val volume: Float? = null,
    val flaconTypeId: Int? = null,
    val consumptionFrequencies: List<ConsumptionFrequencyData> = emptyList(),
    val vapeActions: List<VapeActionData> = emptyList()
)