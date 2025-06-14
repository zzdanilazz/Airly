package ru.health.featuredashboard.impl.presentation.startup_parameters

import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.health.core.api.data.date.DateFormatter
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.FlaconType
import ru.health.core.api.presentation.component.ComponentViewModel
import ru.health.featuredashboard.api.domain.model.StartupParameters
import ru.health.featuredashboard.api.domain.usecase.GetIsStartupParametersSavedUseCase
import ru.health.featuredashboard.api.domain.usecase.SaveStartupParametersUseCase
import ru.health.featureliquid.api.domain.model.Device
import ru.health.featureliquid.api.domain.model.FlaconParams
import java.util.Date

internal class StartupParametersViewModel @AssistedInject constructor(
    private val saveStartupParametersUseCase: SaveStartupParametersUseCase,
    private val getIsStartupParametersSavedUseCase: GetIsStartupParametersSavedUseCase,
    private val dateFormatter: DateFormatter
) : ComponentViewModel() {

    private val _state = MutableStateFlow(StartupParametersUiState())
    val state: StateFlow<StartupParametersUiState> = _state.asStateFlow()

    private val _navEvent = Channel<StartupParametersNavEvent>()
    val navEvent = _navEvent.receiveAsFlow()

    init {
        launch {
            getIsStartupParametersSavedUseCase().onSuccess {  isSaved ->
                if (isSaved) _navEvent.send(StartupParametersNavEvent.OpenApp)
            }
        }
    }

    fun onAction(action: StartupParametersAction) = launch {
        when (action) {
            is StartupParametersAction.SelectDeviceType -> selectDeviceType(action.deviceType)
            is StartupParametersAction.ChangeDeviceBuyPeriod -> changeBuyPeriod(action.buyPeriod)
            is StartupParametersAction.ChangeDevicePrice -> changePrice(action.price)
            is StartupParametersAction.ChangeVaporizerBuyPeriod -> changeVaporizerBuyPeriod(action.buyPeriod)
            is StartupParametersAction.ChangeVaporizerPrice -> changeVaporizerPrice(action.price)
            is StartupParametersAction.SelectBottleType -> selectFlaconType(action.flaconType)
            is StartupParametersAction.SelectInterest -> selectInterest(action.interestIndex)
            is StartupParametersAction.OnLiquidEdited -> onLiquidEdited(action.editedVolume)
            StartupParametersAction.OnLiquidLevelClick -> onLiquidLevelClick()
            StartupParametersAction.OnFinished -> onFinished()
        }
    }

    private fun selectDeviceType(deviceType: DeviceType) {
        _state.update { uiState -> uiState.copy(deviceType = deviceType) }
    }

    private fun changePrice(price: String) {
        _state.update { uiState -> uiState.copy(pricePerDevice = price) }
    }

    private fun changeBuyPeriod(buyPeriod: String) {
        _state.update { uiState -> uiState.copy(deviceBuyPeriod = buyPeriod) }
    }

    private fun changeVaporizerPrice(price: String) {
        _state.update { uiState -> uiState.copy(pricePerVaporizer = price) }
    }

    private fun selectFlaconType(flaconType: FlaconType) {
        _state.update { uiState ->
            uiState.copy(
                flaconParams = FlaconParams(
                    volume = flaconType.volume,
                    flaconType = flaconType
                )
            )
        }
    }

    private fun changeVaporizerBuyPeriod(buyPeriod: String) {
        _state.update { uiState -> uiState.copy(vaporizerBuyPeriod = buyPeriod) }
    }

    private fun selectInterest(interestIndex: Int) {
        _state.update { uiState ->
            val updatedInterests = uiState.interests.mapIndexed { index, interest ->
                if (index == interestIndex) {
                    interest.copy(selected = !interest.selected)
                } else interest
            }
            uiState.copy(interests = updatedInterests)
        }
    }

    private fun onLiquidEdited(editedVolume: Float) {
        _state.update { uiState ->
            uiState.copy(flaconParams = uiState.flaconParams?.copy(volume = editedVolume))
        }
    }

    private suspend fun onLiquidLevelClick() {
        val currentFlaconParams = _state.value.flaconParams
        val filledFlaconParams = currentFlaconParams?.copy(
            volume = currentFlaconParams.flaconType.volume
        )
        filledFlaconParams?.let {
            _navEvent.send(StartupParametersNavEvent.OpenInputLiquid(it))
        }
    }

    private suspend fun onFinished() = with(_state.value) {
        deviceType?.let {
            val currentDate = dateFormatter.formatDate(Date())
            val primaryDevice = Device(
                deviceType = deviceType,
                date = currentDate,
                price = pricePerDevice.toInt(),
                flaconParams = flaconParams
            )

            val secondaryDevice = if (deviceType == DeviceType.POD) {
                Device(
                    deviceType = DeviceType.VAPORIZER,
                    date = currentDate,
                    price = pricePerVaporizer.toInt()
                )
            } else null

            val selectedInterests = interests
                .filter { it.selected }
                .map { it.name }
                .toSet()

            val startupParameters = StartupParameters(
                interests = selectedInterests,
                primaryDevice = primaryDevice,
                secondaryDevice = secondaryDevice,
                pricePerPrimaryDevice = pricePerDevice.toInt(),
                primaryDeviceBuyPeriod = deviceBuyPeriod.toInt(),
                pricePerSecondaryDevice = pricePerVaporizer
                    .ifEmpty { null }
                    ?.toInt(),
                secondaryDeviceBuyPeriod = vaporizerBuyPeriod
                    .ifEmpty { null }
                    ?.toInt()
            )

            saveStartupParametersUseCase(startupParameters).onSuccess {
                _navEvent.send(StartupParametersNavEvent.OpenApp)
            }
        }
    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(): StartupParametersViewModel
    }
}