package ru.health.featureliquid.impl.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun getDeviceById(deviceId: Int) =
        deviceDao.deviceById(deviceId)?.toData()

    override suspend fun getEarliestDevice(isPrimary: Boolean) =
        deviceDao.earliestDevice(isPrimary)?.toData()

    override suspend fun getLatestDeviceFlow(isPrimary: Boolean): Flow<DeviceData?> =
        deviceDao.latestDeviceFlow(isPrimary).map { it?.toData() }

    override suspend fun getAllDevices(): List<DeviceData> =
        deviceDao.allDevices().map { it.toData() }

    override suspend fun saveDevice(device: DeviceData) =
        deviceDao.insert(device.toLocal()).toInt()

    override suspend fun updateDevice(device: DeviceData) =
        deviceDao.update(device.toLocal())

    override suspend fun saveConsumption(consumption: ConsumptionData) =
        consumptionDao.insert(consumption.toLocal())

    override suspend fun getConsumptionCountFlow(includeInitial: Boolean): Flow<Int> =
        if (includeInitial) consumptionDao.consumptionCountFlow()
        else consumptionDao.consumptionCountFlowWithoutInitial()

    override suspend fun getLatestConsumptionDateFlow(deviceId: Int, hasDuration: Boolean): Flow<Date?> =
        if (hasDuration) {
            consumptionDao.getLatestConsumptionDateWithPositiveDuration(deviceId)
        } else consumptionDao.getLatestConsumptionDate(deviceId)

    override suspend fun getFirstConsumptionDate(deviceId: Int?): Date? = deviceId?.let {
        consumptionDao.getFirstConsumptionDateById(it)
    } ?: consumptionDao.getFirstConsumptionDate()


    override suspend fun updateConsumptions(consumptions: List<ConsumptionData>) {
        consumptionDao.updateConsumptions(consumptions.map { it.toLocal() })
    }

    override suspend fun isConsumptionAdded(): Boolean = consumptionDao.isConsumptionAdded()

    override suspend fun isMeasureConsumptionAdded(): Boolean = consumptionDao.isMeasureConsumptionAdded()
}
