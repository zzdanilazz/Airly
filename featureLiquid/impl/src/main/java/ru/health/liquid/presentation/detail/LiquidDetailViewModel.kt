package ru.health.liquid.presentation.detail

import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.health.core.presentation.component.ComponentViewModel
import ru.health.liquid.domain.model.VapeProduct
import ru.health.liquid.domain.usecase.GetVapeProductUseCase

internal class LiquidDetailViewModel @AssistedInject constructor(
    private val getVapeProductUseCase: GetVapeProductUseCase
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
            is LiquidDetailAction.SwitchDeviceType -> switchDevice(action.isPodSelected)
            is LiquidDetailAction.EditLiquidLevel -> editLiquidLevel(action.liquid)
        }
    }

    private suspend fun editLiquidLevelApprove() {

    }

    private suspend fun switchDevice(isPodSelected: Boolean) {
        getVapeProductUseCase(isPodSelected)
            .onSuccess {
                _state.update { uiState -> uiState.copy(vapeProduct = it) }
            }
            .onFailure {
                //todo approve
            }

    }

    private suspend fun addAtomizer() {

    }

    private suspend fun addLiquidBottle() {

    }

    private suspend fun editLiquidLevel(liquid: VapeProduct.Liquid) {

    }

    private suspend fun init() {
        getVapeProductUseCase().onSuccess {
            _state.update { uiState -> uiState.copy(vapeProduct = it) }
        }
    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(): LiquidDetailViewModel
    }
}