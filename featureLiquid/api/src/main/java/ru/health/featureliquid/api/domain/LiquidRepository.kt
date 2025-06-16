package ru.health.featureliquid.api.domain

import kotlinx.coroutines.flow.Flow
import ru.health.featureliquid.api.domain.model.Consumption
import ru.health.featureliquid.api.domain.model.Device
import java.util.Date

interface LiquidRepository {

    suspend fun getDeviceByTypeId(deviceTypeId: Int): Device?

    suspend fun getDeviceById(deviceId: Int): Device?

    suspend fun getEarliestDevice(isPrimary: Boolean = true): Device?

    suspend fun getLatestDeviceFlow(isPrimary: Boolean = true): Flow<Device?>

    suspend fun getAllDevices(
        startDate: Date? = null,
        endDate: Date? = null
    ): List<Device>

    suspend fun updateDevice(device: Device)

    suspend fun saveDevice(device: Device): Int

    suspend fun saveConsumption(consumption: Consumption)

    suspend fun getConsumptionCountFlow(includeInitial: Boolean = false): Flow<Int>

    suspend fun getLatestConsumptionDateFlow(deviceId: Int, hasDuration: Boolean = true): Flow<Date?>

    suspend fun getFirstConsumptionDate(deviceId: Int? = null): Date?

    suspend fun updateConsumptions(consumptions: List<Consumption>)

    suspend fun isConsumptionAdded(): Boolean

    suspend fun isMeasureConsumptionAdded(): Boolean
}
