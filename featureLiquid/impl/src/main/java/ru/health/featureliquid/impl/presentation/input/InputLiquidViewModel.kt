package ru.health.featureliquid.impl.presentation.input

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.health.core.api.presentation.component.ComponentViewModel
import ru.health.featureliquid.api.domain.model.FlaconParams

internal class InputLiquidViewModel @AssistedInject constructor(
    @Assisted isPositiveVolume: Boolean,
    @Assisted flaconParams: FlaconParams
) : ComponentViewModel() {

    private val defaultState = InputLiquidUiState(
        flaconParams = flaconParams,
        isPositiveVolume = isPositiveVolume
    )
    private val _state = MutableStateFlow(defaultState)
    val state: StateFlow<InputLiquidUiState> = _state.asStateFlow()

    private val _navEvent = Channel<InputLiquidNavEvent>()
    val navEvent = _navEvent.receiveAsFlow()

    fun onAction(action: InputLiquidAction) = launch {
        when (action) {
            InputLiquidAction.Init -> init()
            InputLiquidAction.Back -> back()
            is InputLiquidAction.OnSave -> onSave()
            is InputLiquidAction.OnVolumeChange -> onVolumeChange(action.volume)
        }
    }

    private suspend fun init() {

    }

    private suspend fun back() {
        _navEvent.send(InputLiquidNavEvent.Back)
    }

    private suspend fun onSave() {
        _navEvent.send(InputLiquidNavEvent.OnLiquidEdited(_state.value.editedVolume))
    }

    private fun onVolumeChange(volume: Float) {
        _state.update { uiState -> uiState.copy(editedVolume = volume) }
    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(
            isPositiveVolume: Boolean,
            flaconParams: FlaconParams
        ): InputLiquidViewModel
    }
}