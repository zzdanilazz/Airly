package ru.health.featureliquid.impl.data

import ru.health.core.api.data.date.DateFormatter
import ru.health.database.api.device.DeviceDao
import ru.health.database.api.device.consumption_frequency.ConsumptionFrequencyDao
import ru.health.database.api.device.vape_action.VapeActionDao
import ru.health.featureliquid.api.data.ConsumptionFrequencyData
import ru.health.featureliquid.api.data.DeviceData
import ru.health.featureliquid.api.data.LiquidLocalDataSource
import ru.health.featureliquid.api.data.VapeActionData
import javax.inject.Inject

internal class DefaultLiquidLocalDataSource @Inject constructor(
    private val deviceDao: DeviceDao,
    private val consumptionFrequencyDao: ConsumptionFrequencyDao,
    private val vapeActionDao: VapeActionDao,
    private val dateFormatter: DateFormatter
) : LiquidLocalDataSource {

    override suspend fun getDeviceByTypeId(deviceTypeId: Int) =
        deviceDao.deviceByDeviceTypeId(deviceTypeId)?.toData(dateFormatter)

    override suspend fun getLatestDevice(isPrimary: Boolean) =
        deviceDao.latestDevice(isPrimary)?.toData(dateFormatter)

    override suspend fun saveDevice(device: DeviceData) =
        deviceDao.insert(device.toLocal(dateFormatter)).toInt()

    override suspend fun saveConsumptionFrequency(consumptionFrequency: ConsumptionFrequencyData) =
        consumptionFrequencyDao.insert(consumptionFrequency.toLocal())

    override suspend fun saveVapeAction(vapeAction: VapeActionData) =
        vapeActionDao.insert(vapeAction.toLocal(dateFormatter))
}
