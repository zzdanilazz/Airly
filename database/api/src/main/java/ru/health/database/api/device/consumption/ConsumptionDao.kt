package ru.health.database.api.device.consumption

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ConsumptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(consumptionFrequency: ConsumptionLocal)

    @Query("SELECT COUNT(*) FROM ConsumptionLocal")
    fun consumptionCountFlow(): Flow<Int>

    @Query("SELECT COUNT(*) FROM ConsumptionLocal WHERE id != 0")
    fun consumptionCountFlowWithoutInitial(): Flow<Int>

    @Query("SELECT date FROM ConsumptionLocal WHERE deviceId = :deviceId ORDER BY date DESC LIMIT 1")
    fun getLatestConsumptionDate(deviceId: Int): Flow<Date?>

    @Query("""
        SELECT date FROM ConsumptionLocal
        WHERE deviceId = :deviceId AND durationInDays > 0
        ORDER BY date DESC LIMIT 1
    """)
    fun getLatestConsumptionDateWithPositiveDuration(deviceId: Int): Flow<Date?>

    @Query("SELECT date FROM ConsumptionLocal WHERE deviceId = :deviceId ORDER BY date ASC LIMIT 1")
    suspend fun getFirstConsumptionDate(deviceId: Int): Date

    @Update
    suspend fun updateConsumptions(consumptions: List<ConsumptionLocal>)

}