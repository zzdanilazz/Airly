package ru.health.featureliquid.api.data

import kotlinx.coroutines.flow.Flow
import java.util.Date

interface LiquidLocalDataSource {

    suspend fun getDeviceByTypeId(deviceTypeId: Int): DeviceData?

    suspend fun getDeviceById(deviceId: Int): DeviceData?

    suspend fun getEarliestDevice(isPrimary: Boolean): DeviceData?

    suspend fun getLatestDeviceFlow(isPrimary: Boolean): Flow<DeviceData?>

    suspend fun getAllDevices(): List<DeviceData>

    suspend fun saveDevice(device: DeviceData): Int

    suspend fun updateDevice(device: DeviceData)

    suspend fun saveConsumption(consumption: ConsumptionData)

    suspend fun getLatestConsumptionDateFlow(deviceId: Int, hasDuration: Boolean): Flow<Date?>

    suspend fun getFirstConsumptionDate(deviceId: Int): Date

    suspend fun updateConsumptions(consumptions: List<ConsumptionData>)
}
