package ru.health.featureliquid.impl.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.health.core.api.data.date.DateFormatter
import ru.health.featureliquid.api.data.LiquidLocalDataSource
import ru.health.featureliquid.api.domain.LiquidRepository
import ru.health.featureliquid.api.domain.model.Consumption
import ru.health.featureliquid.api.domain.model.Device
import java.util.Date
import javax.inject.Inject

internal class DefaultLiquidRepository @Inject constructor(
    private val liquidLocalDataSource: LiquidLocalDataSource,
    private val dateFormatter: DateFormatter
) : LiquidRepository {

    override suspend fun getDeviceByTypeId(deviceTypeId: Int): Device? =
        withContext(Dispatchers.IO) {
            liquidLocalDataSource.getDeviceByTypeId(deviceTypeId)?.toDomain(dateFormatter)
        }

    override suspend fun getLatestDevice(isPrimary: Boolean): Device? = withContext(Dispatchers.IO) {
        liquidLocalDataSource.getLatestDevice(isPrimary)?.toDomain(dateFormatter)
    }

    override suspend fun getAllDevices(): List<Device> = withContext(Dispatchers.IO) {
        liquidLocalDataSource.getAllDevices().map { it.toDomain(dateFormatter) }
    }

    override suspend fun saveDevice(device: Device): Int = withContext(Dispatchers.IO) {
        liquidLocalDataSource.saveDevice(device.toData(dateFormatter))
    }

    override suspend fun saveConsumptionFrequency(consumption: Consumption) =
        withContext(Dispatchers.IO) {
            liquidLocalDataSource.saveConsumptionFrequency(consumption.toData(dateFormatter))
        }

    override suspend fun getLastConsumptionDate(deviceId: Int): Date = withContext(Dispatchers.IO) {
        liquidLocalDataSource.getLastConsumptionDate(deviceId)
    }

    override suspend fun getFirstConsumptionDate(deviceId: Int): Date = withContext(Dispatchers.IO) {
        liquidLocalDataSource.getFirstConsumptionDate(deviceId)
    }
}
