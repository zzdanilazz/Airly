package ru.health.database.impl

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.health.database.api.device.DeviceDao
import ru.health.database.api.device.DeviceLocal
import ru.health.database.api.device.consumption_frequency.ConsumptionFrequencyDao
import ru.health.database.api.device.consumption_frequency.ConsumptionFrequencyLocal
import ru.health.database.api.device.vape_action.VapeActionDao
import ru.health.database.api.device.vape_action.VapeActionLocal

internal interface AirlyDatabase {

    fun deviceDao(): DeviceDao

    fun consumptionFrequencyDao(): ConsumptionFrequencyDao

    fun vapeActionDao(): VapeActionDao
}

internal abstract class AbstractAirlyDatabase(
    private val context: Context,
    private val databaseName: String
) : AirlyDatabase {

    @Database(
        entities = [
            DeviceLocal::class,
            ConsumptionFrequencyLocal::class,
            VapeActionLocal::class,
        ],
        version = 1,
        exportSchema = false
    )
    @TypeConverters(DateConverter::class)
    internal abstract class ContentRoom : RoomDatabase() {
        abstract fun deviceDao(): DeviceDao
        abstract fun consumptionFrequencyDao(): ConsumptionFrequencyDao
        abstract fun vapeActionDao(): VapeActionDao
    }

    private var db: ContentRoom? = null

    private val database = db ?: run {
        createDatabase()
    }

    override fun deviceDao(): DeviceDao = database.deviceDao()

    override fun consumptionFrequencyDao(): ConsumptionFrequencyDao = database.consumptionFrequencyDao()

    override fun vapeActionDao(): VapeActionDao = database.vapeActionDao()

    private fun createDatabase(): ContentRoom {
        val newDatabaseBuilder = Room
            .databaseBuilder(context, ContentRoom::class.java, databaseName)
        val newDatabase = newDatabaseBuilder.build()
        db = newDatabase
        return newDatabase
    }
}

internal class AirlyRoom(context: Context) : AbstractAirlyDatabase(context, "airly_database")
