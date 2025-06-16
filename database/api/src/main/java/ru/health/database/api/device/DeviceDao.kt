package ru.health.database.api.device

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(device: DeviceLocal): Long

    @Update
    suspend fun update(device: DeviceLocal)

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
        ORDER BY date ASC
        LIMIT 1
    """
    )
    suspend fun earliestDevice(isPrimary: Boolean): DeviceWithDetails?

    @Transaction
    @Query(
        """
        SELECT * FROM DeviceLocal
        WHERE isPrimary = :isPrimary
        ORDER BY date DESC
        LIMIT 1
    """
    )
    fun latestDeviceFlow(isPrimary: Boolean): Flow<DeviceWithDetails?>

    @Transaction
    @Query("SELECT * FROM DeviceLocal")
    suspend fun allDevices(): List<DeviceWithDetails>
}