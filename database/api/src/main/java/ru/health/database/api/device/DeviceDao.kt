package ru.health.database.api.device

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(device: DeviceLocal): Long

    @Transaction
    @Query("SELECT * FROM DeviceLocal WHERE deviceId =:id")
    suspend fun deviceById(id: Int): DeviceWithDetails?

    @Transaction
    @Query("SELECT * FROM DeviceLocal WHERE deviceTypeId =:deviceTypeId")
    suspend fun deviceByDeviceTypeId(deviceTypeId: Int): DeviceWithDetails?

    @Transaction
    @Query(
        """
        SELECT * FROM DeviceLocal
        WHERE isPrimary = :isPrimary
        ORDER BY deviceId DESC
        LIMIT 1
    """
    )
    suspend fun latestDevice(isPrimary: Boolean): DeviceWithDetails?
}