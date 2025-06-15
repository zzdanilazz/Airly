package ru.health.featureliquid.impl.data

import ru.health.core.api.data.date.DateFormatter
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.FlaconType
import ru.health.database.api.device.DeviceLocal
import ru.health.database.api.device.DeviceWithDetails
import ru.health.database.api.device.consumption.ConsumptionLocal
import ru.health.featureliquid.api.data.ConsumptionData
import ru.health.featureliquid.api.data.DeviceData
import ru.health.featureliquid.api.domain.model.Consumption
import ru.health.featureliquid.api.domain.model.Device
import ru.health.featureliquid.api.domain.model.FlaconParams
import java.util.Date

internal fun Device.toData(dateFormatter: DateFormatter) = DeviceData(
    id = id,
    deviceTypeId = deviceType.id,
    isPrimary = deviceType.isPrimary,
    date = dateFormatter.formatToDate(date) ?: Date(),
    price = price,
    volume = flaconParams?.volume,
    flaconTypeId = flaconParams?.flaconType?.id
)

internal fun DeviceData.toDomain(dateFormatter: DateFormatter) = Device(
    id = id,
    deviceType = DeviceType.entries.find { it.id == deviceTypeId } ?: DeviceType.POD,
    date = dateFormatter.formatDate(date),
    price = price,
    flaconParams = FlaconParams(
        volume = volume ?: 0f,
        flaconType = FlaconType.entries.find { it.id == flaconTypeId } ?: FlaconType.SMALL
    ),
    consumptions = consumptionFrequencies.map { it.toDomain(dateFormatter) }
)

internal fun DeviceData.toLocal() = DeviceLocal(
    deviceId = id,
    deviceTypeId = deviceTypeId,
    isPrimary = isPrimary,
    date = date,
    price = price,
    volume = volume,
    flaconTypeId = flaconTypeId
)

internal fun DeviceWithDetails.toData() = DeviceData(
    id = device.deviceId,
    deviceTypeId = device.deviceTypeId,
    isPrimary = device.isPrimary,
    date = device.date,
    price = device.price,
    volume = device.volume,
    flaconTypeId = device.flaconTypeId,
    consumptionFrequencies = consumptions.map { it.toData() }
)

internal fun Consumption.toData(dateFormatter: DateFormatter) = ConsumptionData(
    id = id,
    frequency = frequency,
    durationInDays = durationInDays,
    isMeasured = isMeasured,
    deviceId = deviceId,
    date = dateFormatter.formatToDate(date) ?: Date()
)

internal fun ConsumptionData.toDomain(dateFormatter: DateFormatter) = Consumption(
    id = id,
    frequency = frequency,
    durationInDays = durationInDays,
    isMeasured = isMeasured,
    deviceId = deviceId,
    date = dateFormatter.formatDate(date)
)

internal fun ConsumptionData.toLocal() = ConsumptionLocal(
    id = id,
    frequency = frequency,
    durationInDays = durationInDays,
    isMeasured = isMeasured,
    deviceId = deviceId,
    date = date
)

internal fun ConsumptionLocal.toData() = ConsumptionData(
    id = id,
    frequency = frequency,
    durationInDays = durationInDays,
    isMeasured = isMeasured,
    deviceId = deviceId,
    date = date
)