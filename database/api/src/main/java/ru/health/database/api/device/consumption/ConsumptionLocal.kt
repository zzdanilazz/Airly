package ru.health.database.api.device.consumption

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.health.database.api.device.DeviceLocal
import java.util.Date

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
data class ConsumptionLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val frequency: Float,
    val durationInDays: Float,
    val isMeasured: Boolean,
    val deviceId: Int,
    val date: Date
)