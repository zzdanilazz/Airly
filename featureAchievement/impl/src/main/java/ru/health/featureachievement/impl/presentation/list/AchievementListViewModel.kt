package ru.health.featureachievement.impl.presentation.list

import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.health.core.api.presentation.component.ComponentViewModel
import ru.health.featureachievement.api.domain.usecase.GetAchievementMapUseCase

internal class AchievementListViewModel @AssistedInject constructor(
    private val getAchievementMapUseCase: GetAchievementMapUseCase,
) : ComponentViewModel() {

    private val _state = MutableStateFlow(AchievementListUiState())
    val state: StateFlow<AchievementListUiState> = _state.asStateFlow()

    private val _navEvent = Channel<AchievementListNavEvent>()
    val navEvent = _navEvent.receiveAsFlow()

    fun onAction(action: AchievementListAction) = launch {
        when (action) {
            AchievementListAction.Init -> init()
        }
    }

    private suspend fun init() {
        getAchievementMapUseCase().onSuccess { map ->
            val flows = map.mapValues { (_, flowList) -> flowList }

            combine(flows.values) { arrays ->
                flows.keys.zip(arrays).toMap()
            }.collect { updatedMap ->
                _state.update { it.copy(achievementMap = updatedMap) }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(): AchievementListViewModel
    }
}