package ru.health.database.api.device.vape_action

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
data class VapeActionLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val puffs: Int,
    val deviceId: Int,
    val date: String
)