package ru.health.database.api.device.consumption_frequency

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.health.database.api.device.DeviceLocal

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = DeviceLocal::class,
            parentColumns = arrayOf("deviceId"),
            childColumns = arrayOf("deviceId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ConsumptionFrequencyLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val value: Float,
    val deviceId: Int
)