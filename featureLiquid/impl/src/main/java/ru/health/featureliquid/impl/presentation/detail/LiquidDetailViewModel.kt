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
import ru.health.core.api.domain.FlaconParams
import ru.health.core.api.presentation.component.ComponentViewModel
import ru.health.featureliquid.api.domain.usecase.GetDeviceUseCase

internal class LiquidDetailViewModel @AssistedInject constructor(
    private val getDeviceUseCase: GetDeviceUseCase
) : ComponentViewModel() {

    private val _state = MutableStateFlow(LiquidDetailUiState())
    val state: StateFlow<LiquidDetailUiState> = _state.asStateFlow()

    private val _navEvent = Channel<LiquidDetailNavEvent>()
    val navEvent = _navEvent.receiveAsFlow()

    fun onAction(action: LiquidDetailAction) = launch {
        when (action) {
            LiquidDetailAction.Init -> init()
            LiquidDetailAction.EditLiquidLevelApprove -> editLiquidLevelApprove()
            LiquidDetailAction.AddLiquidBottle -> addLiquidBottle()
            LiquidDetailAction.AddAtomizer -> addAtomizer()
            LiquidDetailAction.AddDisposableApprove -> addDisposableApprove()
            LiquidDetailAction.AddPuffsApprove -> addPuffsApprove()
            is LiquidDetailAction.SwitchDeviceType -> switchDevice(action.deviceType)
            is LiquidDetailAction.EditLiquidLevel -> editLiquidLevel(action.flaconParams)
        }
    }

    private suspend fun switchDevice(deviceType: DeviceType) {
        getDeviceUseCase(deviceType)
            .onSuccess {
                _state.update { uiState -> uiState.copy(device = it) }
            }
            .onFailure {
                //todo approve
            }

    }

    private suspend fun editLiquidLevelApprove() {
        state.value.device?.flaconParams?.let {
            editLiquidLevel(it)
        }
    }

    private suspend fun addLiquidBottle() {

    }

    private suspend fun addAtomizer() {

    }

    private suspend fun editLiquidLevel(flaconParams: FlaconParams) {
        _navEvent.send(LiquidDetailNavEvent.EditLiquidLevel(flaconParams))
    }

    private suspend fun addDisposableApprove() {

    }

    private suspend fun addPuffsApprove() {

    }

    private suspend fun init() {
        getDeviceUseCase().onSuccess {
            _state.update { uiState -> uiState.copy(device = it) }
        }
    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(): LiquidDetailViewModel
    }
}