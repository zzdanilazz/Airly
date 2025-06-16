package ru.health.featureliquid.impl.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override suspend fun getDeviceById(deviceId: Int): Device? = withContext(Dispatchers.IO) {
        liquidLocalDataSource.getDeviceById(deviceId)?.toDomain(dateFormatter)
    }

    override suspend fun getEarliestDevice(isPrimary: Boolean): Device? = withContext(Dispatchers.IO) {
        liquidLocalDataSource.getEarliestDevice(isPrimary)?.toDomain(dateFormatter)
    }

    override suspend fun getLatestDeviceFlow(isPrimary: Boolean): Flow<Device?> = withContext(Dispatchers.IO) {
        liquidLocalDataSource.getLatestDeviceFlow(isPrimary).map { it?.toDomain(dateFormatter) }
    }

    override suspend fun getAllDevices(): List<Device> = withContext(Dispatchers.IO) {
        liquidLocalDataSource.getAllDevices().map { it.toDomain(dateFormatter) }
    }

    override suspend fun updateDevice(device: Device) = withContext(Dispatchers.IO) {
        liquidLocalDataSource.updateDevice(device.toData(dateFormatter))
    }

    override suspend fun saveDevice(device: Device): Int = withContext(Dispatchers.IO) {
        liquidLocalDataSource.saveDevice(device.toData(dateFormatter))
    }

    override suspend fun saveConsumption(consumption: Consumption) =
        withContext(Dispatchers.IO) {
            liquidLocalDataSource.saveConsumption(consumption.toData(dateFormatter))
        }

    override suspend fun getConsumptionCountFlow(includeInitial: Boolean): Flow<Int> =
        withContext(Dispatchers.IO) {
            liquidLocalDataSource.getConsumptionCountFlow(includeInitial)
        }

    override suspend fun getLatestConsumptionDateFlow(deviceId: Int, hasDuration: Boolean): Flow<Date?> =
        withContext(Dispatchers.IO) {
            liquidLocalDataSource.getLatestConsumptionDateFlow(deviceId, hasDuration)
        }

    override suspend fun getFirstConsumptionDate(deviceId: Int?): Date? = withContext(Dispatchers.IO) {
        liquidLocalDataSource.getFirstConsumptionDate(deviceId)
    }

    override suspend fun updateConsumptions(consumptions: List<Consumption>) =
        withContext(Dispatchers.IO) {
            liquidLocalDataSource.updateConsumptions(consumptions.map { it.toData(dateFormatter) })
        }

    override suspend fun isConsumptionAdded(): Boolean = withContext(Dispatchers.IO) {
        liquidLocalDataSource.isConsumptionAdded()
    }

    override suspend fun isMeasureConsumptionAdded(): Boolean = withContext(Dispatchers.IO) {
        liquidLocalDataSource.isMeasureConsumptionAdded()
    }
}
