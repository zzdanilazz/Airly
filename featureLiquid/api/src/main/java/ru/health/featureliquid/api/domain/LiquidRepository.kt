package ru.health.featureliquid.api.domain

import ru.health.featureliquid.api.domain.model.Consumption
import ru.health.featureliquid.api.domain.model.Device
import java.util.Date

interface LiquidRepository {

    suspend fun getDeviceByTypeId(deviceTypeId: Int): Device?

    suspend fun getLatestDevice(isPrimary: Boolean = true): Device?

    suspend fun getAllDevices(): List<Device>

    suspend fun saveDevice(device: Device): Int

    suspend fun saveConsumptionFrequency(consumption: Consumption)

    suspend fun getLastConsumptionDate(deviceId: Int): Date

    suspend fun getFirstConsumptionDate(deviceId: Int): Date
}
