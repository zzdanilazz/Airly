package ru.health.featureliquid.impl.data

import ru.health.core.api.data.date.DateFormatter
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.FlaconType
import ru.health.database.api.device.DeviceLocal
import ru.health.database.api.device.DeviceWithDetails
import ru.health.database.api.device.consumption_frequency.ConsumptionFrequencyLocal
import ru.health.database.api.device.vape_action.VapeActionLocal
import ru.health.featureliquid.api.data.ConsumptionFrequencyData
import ru.health.featureliquid.api.data.DeviceData
import ru.health.featureliquid.api.data.VapeActionData
import ru.health.featureliquid.api.domain.model.ConsumptionFrequency
import ru.health.featureliquid.api.domain.model.Device
import ru.health.featureliquid.api.domain.model.FlaconParams
import ru.health.featureliquid.api.domain.model.VapeAction
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
    consumptionFrequencies = consumptionFrequencies.map { it.toDomain() },
    vapeActions = vapeActions.map { it.toDomain(dateFormatter) }
)

internal fun DeviceData.toLocal(dateFormatter: DateFormatter) = DeviceLocal(
    deviceId = id,
    deviceTypeId = deviceTypeId,
    isPrimary = isPrimary,
    date = dateFormatter.formatDate(date),
    price = price,
    volume = volume,
    flaconTypeId = flaconTypeId
)

internal fun DeviceWithDetails.toData(dateFormatter: DateFormatter) = DeviceData(
    id = device.deviceId,
    deviceTypeId = device.deviceTypeId,
    isPrimary = device.isPrimary,
    date = dateFormatter.formatToDate(device.date) ?: Date(),
    price = device.price,
    volume = device.volume,
    flaconTypeId = device.flaconTypeId,
    consumptionFrequencies = consumptionFrequencies.map { it.toData() },
    vapeActions = vapeActions.map { it.toData(dateFormatter) }
)

internal fun ConsumptionFrequency.toData() = ConsumptionFrequencyData(
    id = id,
    value = value,
    deviceId = deviceId
)

internal fun ConsumptionFrequencyData.toDomain() = ConsumptionFrequency(
    id = id,
    value = value,
    deviceId = deviceId
)

internal fun ConsumptionFrequencyData.toLocal() = ConsumptionFrequencyLocal(
    id = id,
    value = value,
    deviceId = deviceId
)

internal fun ConsumptionFrequencyLocal.toData() = ConsumptionFrequencyData(
    id = id,
    value = value,
    deviceId = deviceId
)

internal fun VapeAction.toData(dateFormatter: DateFormatter) = VapeActionData(
    id = id,
    puffs = puffs,
    deviceId = deviceId,
    date = dateFormatter.formatToDate(date) ?: Date(),
)

internal fun VapeActionData.toDomain(dateFormatter: DateFormatter) = VapeAction(
    id = id,
    puffs = puffs,
    deviceId = deviceId,
    date = dateFormatter.formatDate(date),
)

internal fun VapeActionData.toLocal(dateFormatter: DateFormatter) = VapeActionLocal(
    id = id,
    puffs = puffs,
    deviceId = deviceId,
    date = dateFormatter.formatDate(date),
)

internal fun VapeActionLocal.toData(dateFormatter: DateFormatter) = VapeActionData(
    id = id,
    puffs = puffs,
    deviceId = deviceId,
    date = dateFormatter.formatToDate(date) ?: Date(),
)