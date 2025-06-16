package ru.health.featureliquid.impl.presentation.detail

import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.health.core.api.domain.DeviceType
import ru.health.core.api.domain.FlaconType
import ru.health.core.api.presentation.component.ComponentViewModel
import ru.health.featureliquid.api.domain.model.FlaconParams
import ru.health.featureliquid.api.domain.usecase.AddDeviceUseCase
import ru.health.featureliquid.api.domain.usecase.EditLiquidUseCase
import ru.health.featureliquid.api.domain.usecase.GetDeviceUseCase
import ru.health.featureliquid.api.domain.usecase.SaveConsumptionUseCase

internal class LiquidDetailViewModel @AssistedInject constructor(
    private val getDeviceUseCase: GetDeviceUseCase,
    private val addDeviceUseCase: AddDeviceUseCase,
    private val saveConsumptionUseCase: SaveConsumptionUseCase,
    private val editLiquidUseCase: EditLiquidUseCase
) : ComponentViewModel() {

    private val _state = MutableStateFlow(LiquidDetailUiState())
    val state: StateFlow<LiquidDetailUiState> = _state.asStateFlow()

    private val _navEvent = Channel<LiquidDetailNavEvent>()
    val navEvent = _navEvent.receiveAsFlow()

    init {
        launch { init() }
    }

    fun onAction(action: LiquidDetailAction) = launch {
        when (action) {
            LiquidDetailAction.AddVaporizerApprove -> addVaporizerApprove()
            LiquidDetailAction.AddConsumptionApprove -> addConsumptionApprove()
            LiquidDetailAction.EditLiquidLevel -> editLiquidLevel()
            is LiquidDetailAction.AddConsumption -> addConsumption(vapeDuration = action.vapeDurationDays)
            is LiquidDetailAction.SwitchDeviceType -> switchDevice(action.deviceType)
            is LiquidDetailAction.AddPrimaryDeviceApprove -> addPrimaryDeviceApprove(action.deviceType)
            is LiquidDetailAction.OnLiquidEdited -> onLiquidEdited(action.editedVolume)
            is LiquidDetailAction.AddFlacon -> addFlacon(action.price, action.switchIndex)
            is LiquidDetailAction.AddVaporizer -> addVaporizer(action.price)
            is LiquidDetailAction.AddDisposable -> addDisposable(action.price)
        }
    }

    private suspend fun init() {
        getDeviceUseCase().onSuccess {
            _state.update { uiState -> uiState.copy(primaryDevice = it) }
        }
    }

    private suspend fun switchDevice(deviceType: DeviceType) {
        getDeviceUseCase(deviceType)
            .onSuccess {
                _state.update { uiState -> uiState.copy(primaryDevice = it) }
            }
            .onFailure {
                //todo approve
            }
    }

    private suspend fun editLiquidLevel() {
        state.value.primaryDevice?.flaconParams?.let {
            _navEvent.send(LiquidDetailNavEvent.EditLiquidLevel(it))
        }
    }

    private suspend fun addPrimaryDeviceApprove(deviceType: DeviceType) {
        _navEvent.send(LiquidDetailNavEvent.AddPrimaryDevice(deviceType))
    }

    private suspend fun addVaporizerApprove() {
        _navEvent.send(LiquidDetailNavEvent.AddVaporizerApprove)
    }

    private suspend fun onLiquidEdited(editedVolume: Float) {
        addConsumption(
            isMeasured = true,
            liquidDelta = _state.value.primaryDevice?.flaconParams?.let {
                (it.volume  - editedVolume) / it.flaconType.volume
            }
        )
        editLiquidUseCase(editedVolume).onSuccess {
            _state.update { uiState ->
                val device = uiState.primaryDevice
                val flacon = device?.flaconParams

                uiState.copy(
                    primaryDevice = device?.copy(
                        flaconParams = flacon?.copy(volume = editedVolume)
                    )
                )
            }
        }
    }

    private suspend fun addFlacon(price: String, switchIndex: Int) {
        with (_state.value) {
            val flaconType = FlaconType.entries[switchIndex]
            val flaconParams = FlaconParams(
                volume = flaconType.volume,
                flaconType = flaconType
            )
            primaryDevice?.flaconParams?.let { flaconParams ->
                editLiquidUseCase(0f).onSuccess {
                    addConsumption(
                        isMeasured = true,
                        liquidDelta = flaconParams.volume / flaconParams.flaconType.volume
                    )
                }
            }
            addDeviceUseCase(
                deviceType = DeviceType.POD,
                flaconParams = flaconParams,
                price = price.toInt()
            ).onSuccess {
                init()
            }
        }
    }

    private suspend fun addVaporizer(price: Int) {
        addDeviceUseCase(
            deviceType = DeviceType.VAPORIZER,
            price = price
        )
    }

    private suspend fun addDisposable(price: Int) {
        addConsumption(isMeasured = true)
        addDeviceUseCase(
            deviceType = DeviceType.DISPOSABLE,
            price = price
        ).onSuccess {
            init()
        }
    }

    private suspend fun addConsumptionApprove() {
        _navEvent.send(LiquidDetailNavEvent.AddConsumptionApprove)
    }

    private suspend fun addConsumption(
        isMeasured: Boolean = false,
        vapeDuration: Float? = null,
        liquidDelta: Float? = null
    ) = with(_state.value) {
        primaryDevice?.let {
            saveConsumptionUseCase(
                deviceId = primaryDevice.id,
                isMeasured = isMeasured,
                vapeDuration = vapeDuration,
                liquidDelta = liquidDelta
            )
        }
    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(): LiquidDetailViewModel
    }
}