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
import ru.health.core.api.domain.BottleType
import ru.health.core.api.domain.Device
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.presentation.component.ComponentViewModel
import ru.health.featuredashboard.api.domain.usecase.GetIsStartupParametersSavedUseCase
import ru.health.featuredashboard.api.domain.usecase.SaveStartupParametersUseCase

internal class StartupParametersViewModel @AssistedInject constructor(
    private val saveStartupParametersUseCase: SaveStartupParametersUseCase,
    private val getIsStartupParametersSavedUseCase: GetIsStartupParametersSavedUseCase
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
            is StartupParametersAction.SelectBottleType -> selectBottleType(action.bottleType)
            is StartupParametersAction.SelectInterest -> selectInterest(action.interestIndex)
            is StartupParametersAction.OnLiquidEdited -> onLiquidEdited(action.editedVolume)
            StartupParametersAction.OnLiquidLevelClick -> onLiquidLevelClick()
            StartupParametersAction.OnFinished -> onFinished()
        }
    }

    private fun selectDeviceType(deviceType: DeviceType) {
        _state.update { uiState ->
            uiState.copy(
                deviceType = deviceType,
                device = Device(
                    deviceType = deviceType
                )
            )
        }
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

    private fun selectBottleType(bottleType: BottleType) {
        _state.update { uiState ->
            uiState.copy(
                bottleType = bottleType,
                device = uiState.device?.copy(
                    bottleType = bottleType,
                    currentVolume = bottleType.volume
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
            uiState.copy(
                device = uiState.device?.copy(currentVolume = editedVolume)
            )
        }
    }

    private suspend fun onLiquidLevelClick() {
        _state.value.device?.let {
            _navEvent.send(StartupParametersNavEvent.OpenInputLiquid(it))
        }
    }

    private suspend fun onFinished() {
        saveStartupParametersUseCase
        _navEvent.send(StartupParametersNavEvent.OpenApp)
    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(): StartupParametersViewModel
    }
}