package ru.health.featureliquid.api.data

interface LiquidLocalDataSource {

    suspend fun getDeviceByTypeId(deviceTypeId: Int): DeviceData?

    suspend fun getLatestDevice(isPrimary: Boolean): DeviceData?

    suspend fun saveDevice(device: DeviceData): Int

    suspend fun saveConsumptionFrequency(consumptionFrequency: ConsumptionFrequencyData)

    suspend fun saveVapeAction(vapeAction: VapeActionData)
}
