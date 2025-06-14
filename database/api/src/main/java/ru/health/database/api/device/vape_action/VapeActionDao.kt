package ru.health.database.api.device.vape_action

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface VapeActionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vapeAction: VapeActionLocal)
}