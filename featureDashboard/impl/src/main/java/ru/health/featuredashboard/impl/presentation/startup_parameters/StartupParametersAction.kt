package ru.health.featuredashboard.impl.presentation.startup_parameters

import ru.health.core.api.domain.DeviceType

internal sealed interface StartupParametersAction {

    data object Init : StartupParametersAction

    data class SelectDeviceType(val deviceType: DeviceType) : StartupParametersAction

    data class ChangeDevicePrice(val price: String) : StartupParametersAction

    data class ChangeDeviceBuyPeriod(val buyPeriod: String) : StartupParametersAction

    data class ChangeVaporizerPrice(val price: String) : StartupParametersAction

    data class ChangeVaporizerBuyPeriod(val buyPeriod: String) : StartupParametersAction

    data class SelectInterest(val interestIndex: Int) : StartupParametersAction

    data object OnFinished : StartupParametersAction

}