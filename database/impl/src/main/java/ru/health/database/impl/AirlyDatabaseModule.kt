@file:Suppress("unused")

package ru.health.database.impl

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.health.database.api.device.DeviceDao
import ru.health.database.api.device.consumption_frequency.ConsumptionFrequencyDao
import ru.health.database.api.device.vape_action.VapeActionDao
import javax.inject.Singleton

@Module
class AirlyDatabaseModule {

    internal companion object {

        @Provides
        @Singleton
        fun provideAirlyDatabase(context: Context): AirlyDatabase = AirlyRoom(context)

        @Provides
        @Singleton
        fun provideDeviceDao(database: AirlyDatabase): DeviceDao = database.deviceDao()

        @Provides
        @Singleton
        fun provideConsumptionFrequencyDaoDao(database: AirlyDatabase): ConsumptionFrequencyDao =
            database.consumptionFrequencyDao()

        @Provides
        @Singleton
        fun provideVapeActionDaoDao(database: AirlyDatabase): VapeActionDao = database.vapeActionDao()
    }
}
