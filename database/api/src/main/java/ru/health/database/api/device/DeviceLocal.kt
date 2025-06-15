package ru.health.database.api.device

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import ru.health.database.api.device.consumption.ConsumptionLocal
import java.util.Date

@Entity
data class DeviceLocal(
    @PrimaryKey(autoGenerate = true)
    val deviceId: Int = 0,
    val isPrimary: Boolean,
    val deviceTypeId: Int,
    val date: Date,
    val price: Int,
    val volume: Float? = null,
    val flaconTypeId: Int? = null
)

data class DeviceWithDetails(
    @Embedded val device: DeviceLocal,
    @Relation(parentColumn = "deviceId", entityColumn = "deviceId")
    val consumptions: List<ConsumptionLocal>
)