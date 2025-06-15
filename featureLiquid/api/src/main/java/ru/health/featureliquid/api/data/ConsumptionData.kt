package ru.health.featureliquid.api.data

import java.util.Date

data class ConsumptionData(
    val id: Int = 0,
    val frequency: Float,
    val durationInDays: Float,
    val isMeasured: Boolean,
    val deviceId: Int,
    val date: Date
)