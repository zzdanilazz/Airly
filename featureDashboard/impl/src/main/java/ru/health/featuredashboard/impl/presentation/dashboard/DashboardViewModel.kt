package ru.health.featuredashboard.impl.presentation.dashboard

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.core.content.FileProvider
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
import ru.health.featuredashboard.api.domain.usecase.GetInterestsUseCase
import java.io.File
import java.io.FileOutputStream

internal class DashboardViewModel @AssistedInject constructor(
    private val getDashboardInfoUseCase: GetDashboardInfoUseCase,
    private val getInterestsUseCase: GetInterestsUseCase
) : ComponentViewModel() {

    private val _state = MutableStateFlow(DashboardUiState())
    val state: StateFlow<DashboardUiState> = _state.asStateFlow()

    private val _navEvent = Channel<DashboardNavEvent>()
    val navEvent = _navEvent.receiveAsFlow()

    fun onAction(action: DashboardAction) = launch {
        when (action) {
            DashboardAction.Init -> init()
            is DashboardAction.OnShareClick -> onShareClick(action.context, action.bitmap, action.text)
        }
    }

    private fun init() {
        loadDashboard()
        loadInterests()
    }

    private fun loadDashboard() = launch {
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

    private fun loadInterests() = launch {
        getInterestsUseCase().onSuccess {
            _state.update { uiState -> uiState.copy(interests = it) }
        }
    }

    private fun onShareClick(context: Context, bitmap: ImageBitmap, text: String) {
        val uri = saveBitmapAndGetUri(context, bitmap.asAndroidBitmap())
        val shareIntent = Intent.createChooser(
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_TEXT, text)
                type = "image/png"
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            },
            null
        )
        context.startActivity(shareIntent)
    }

    private fun saveBitmapAndGetUri(context: Context, bitmap: Bitmap): Uri {
        val cachePath = File(context.cacheDir, "images")
        cachePath.mkdirs()
        val file = File(cachePath, "shared_image.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return FileProvider.getUriForFile(
            context, "${context.packageName}.fileprovider", file
        )
    }

    @AssistedFactory
    interface Factory {
        operator fun invoke(): DashboardViewModel
    }
}