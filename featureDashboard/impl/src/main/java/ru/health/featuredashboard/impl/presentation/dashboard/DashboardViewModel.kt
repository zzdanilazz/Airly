package ru.health.featuredashboard.impl.presentation.dashboard

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
import ru.health.featuredashboard.api.domain.usecase.GetDashboardInfoUseCase

internal class DashboardViewModel @AssistedInject constructor(
    private val getDashboardInfoUseCase: GetDashboardInfoUseCase
) : ComponentViewModel() {

    private val _state = MutableStateFlow(DashboardUiState())
    val state: StateFlow<DashboardUiState> = _state.asStateFlow()

    private val _navEvent = Channel<DashboardNavEvent>()
    val navEvent = _navEvent.receiveAsFlow()

    fun onAction(action: DashboardAction) = launch {
        when (action) {
            DashboardAction.Init -> init()
            DashboardAction.OnUploadClick -> onUploadClick()
        }
    }

    private suspend fun init() {
        getDashboardInfoUseCase().onSuccess { dashboardInfo ->
            launch {
                dashboardInfo.abstinenceDuration.collect { abstinenceDuration ->
                    _state.update { uiState ->
                        uiState.copy(
                            hasNotifications = dashboardInfo.hasNotifications,
                            abstinenceDuration = abstinenceDuration
                        )
                    }
                }
            }
            launch {
                dashboardInfo.savedMoneyFlow.collect {
                    _state.update { uiState -> uiState.copy(savedMoney = it) }
                }
            }
            launch {
                dashboardInfo.health.collect {
                    _state.update { uiState -> uiState.copy(health = it) }
                }
            }
        }
    }

    private suspend fun loadDashboard() {

    }

    private suspend fun onUploadClick() {

    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(): DashboardViewModel
    }
}