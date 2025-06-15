package ru.health.database.impl

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.health.database.api.device.DeviceDao
import ru.health.database.api.device.DeviceLocal
import ru.health.database.api.device.consumption.ConsumptionDao
import ru.health.database.api.device.consumption.ConsumptionLocal

internal interface AirlyDatabase {

    fun deviceDao(): DeviceDao

    fun consumptionFrequencyDao(): ConsumptionDao
}

internal abstract class AbstractAirlyDatabase(
    private val context: Context,
    private val databaseName: String
) : AirlyDatabase {

    @Database(
        entities = [
            DeviceLocal::class,
            ConsumptionLocal::class
        ],
        version = 1,
        exportSchema = false
    )
    @TypeConverters(DateConverter::class)
    internal abstract class ContentRoom : RoomDatabase() {
        abstract fun deviceDao(): DeviceDao
        abstract fun consumptionFrequencyDao(): ConsumptionDao
    }

    private var db: ContentRoom? = null

    private val database = db ?: run {
        createDatabase()
    }

    override fun deviceDao(): DeviceDao = database.deviceDao()

    override fun consumptionFrequencyDao(): ConsumptionDao = database.consumptionFrequencyDao()

    private fun createDatabase(): ContentRoom {
        val newDatabaseBuilder = Room
            .databaseBuilder(context, ContentRoom::class.java, databaseName)
        val newDatabase = newDatabaseBuilder.build()
        db = newDatabase
        return newDatabase
    }
}

internal class AirlyRoom(context: Context) : AbstractAirlyDatabase(context, "airly_database")
