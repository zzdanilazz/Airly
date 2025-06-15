package ru.health.featureliquid.api.data

import java.util.Date

interface LiquidLocalDataSource {

    suspend fun getDeviceByTypeId(deviceTypeId: Int): DeviceData?

    suspend fun getLatestDevice(isPrimary: Boolean): DeviceData?

    suspend fun getAllDevices(): List<DeviceData>

    suspend fun saveDevice(device: DeviceData): Int

    suspend fun saveConsumptionFrequency(consumptionFrequency: ConsumptionData)

    suspend fun getLastConsumptionDate(deviceId: Int): Date

    suspend fun getFirstConsumptionDate(deviceId: Int): Date
}
