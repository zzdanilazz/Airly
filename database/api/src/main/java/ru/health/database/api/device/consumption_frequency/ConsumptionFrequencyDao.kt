package ru.health.database.api.device.consumption_frequency

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ConsumptionFrequencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(consumptionFrequency: ConsumptionFrequencyLocal)
}