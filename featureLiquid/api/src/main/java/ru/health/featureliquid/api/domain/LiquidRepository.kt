package ru.health.featureliquid.api.domain

import ru.health.featureliquid.api.domain.model.ConsumptionFrequency
import ru.health.featureliquid.api.domain.model.Device
import ru.health.featureliquid.api.domain.model.VapeAction

interface LiquidRepository {

    suspend fun getDeviceByTypeId(deviceTypeId: Int): Device?

    suspend fun getLatestDevice(isPrimary: Boolean = true): Device?

    suspend fun saveDevice(device: Device): Int

    suspend fun saveConsumptionFrequency(consumptionFrequency: ConsumptionFrequency)

    suspend fun saveVapeAction(vapeAction: VapeAction)

}
