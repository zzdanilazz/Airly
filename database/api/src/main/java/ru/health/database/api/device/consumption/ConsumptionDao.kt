package ru.health.database.api.device.consumption

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.Date

@Dao
interface ConsumptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(consumptionFrequency: ConsumptionLocal)

    @Query("SELECT date FROM ConsumptionLocal WHERE deviceId = :deviceId ORDER BY date DESC LIMIT 1")
    suspend fun getLastConsumptionDate(deviceId: Int): Date

    @Query("SELECT date FROM ConsumptionLocal WHERE deviceId = :deviceId ORDER BY date ASC LIMIT 1")
    suspend fun getFirstConsumptionDate(deviceId: Int): Date

}