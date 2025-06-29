package ru.health.airly

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import ru.health.core.api.data.date.DateFormatter
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.FlaconType
import ru.health.core.api.domain.result.RootResult
import ru.health.featureliquid.api.domain.LiquidRepository
import ru.health.featureliquid.api.domain.model.FlaconParams
import ru.health.featureliquid.impl.domain.DefaultAddDeviceUseCase
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultAddDeviceUseCaseTest {

    @Test
    fun invokeShouldSaveDeviceDndReturnSuccess() = runTest {
        val liquidRepository = mockk<LiquidRepository>()
        val dateFormatter = mockk<DateFormatter>()
        val useCase = DefaultAddDeviceUseCase(liquidRepository, dateFormatter)
        val flaconParams = FlaconParams(FlaconType.SMALL.volume, FlaconType.SMALL)
        val price = 1000
        val formattedDate = "2025-05-30"
        val expectedDeviceId = 42
        every { dateFormatter.formatDate(any()) } returns formattedDate
        coEvery { liquidRepository.saveDevice(any()) } returns expectedDeviceId
        assertTrue(useCase(DeviceType.POD, flaconParams, price) is RootResult.Success)
        coVerify(exactly = 1) {
            liquidRepository.saveDevice(withArg { device ->
                assertEquals(DeviceType.POD, device.deviceType)
                assertEquals(flaconParams, device.flaconParams)
                assertEquals(price, device.price)
                assertEquals(formattedDate, device.date)
            })
        }
    }
}


