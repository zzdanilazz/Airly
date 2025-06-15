package ru.health.featureliquid.impl.data

import ru.health.database.api.device.DeviceDao
import ru.health.database.api.device.consumption.ConsumptionDao
import ru.health.featureliquid.api.data.ConsumptionData
import ru.health.featureliquid.api.data.DeviceData
import ru.health.featureliquid.api.data.LiquidLocalDataSource
import java.util.Date
import javax.inject.Inject

internal class DefaultLiquidLocalDataSource @Inject constructor(
    private val deviceDao: DeviceDao,
    private val consumptionDao: ConsumptionDao
) : LiquidLocalDataSource {

    override suspend fun getDeviceByTypeId(deviceTypeId: Int) =
        deviceDao.deviceByDeviceTypeId(deviceTypeId)?.toData()

    override suspend fun getLatestDevice(isPrimary: Boolean) =
        deviceDao.latestDevice(isPrimary)?.toData()

    override suspend fun getAllDevices(): List<DeviceData> =
        deviceDao.allDevices().map { it.toData() }

    override suspend fun saveDevice(device: DeviceData) =
        deviceDao.insert(device.toLocal()).toInt()

    override suspend fun saveConsumptionFrequency(consumptionFrequency: ConsumptionData) =
        consumptionDao.insert(consumptionFrequency.toLocal())

    override suspend fun getLastConsumptionDate(deviceId: Int): Date =
        consumptionDao.getLastConsumptionDate(deviceId)

    override suspend fun getFirstConsumptionDate(deviceId: Int): Date =
        consumptionDao.getFirstConsumptionDate(deviceId)
}
