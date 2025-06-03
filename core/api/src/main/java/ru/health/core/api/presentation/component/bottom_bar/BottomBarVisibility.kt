package ru.health.core.api.presentation.component.bottom_bar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Stable
interface BottomBarVisibilityState {
    val isVisible: Boolean
    fun hide()
    fun show()
}

@Serializable
private class DefaultBottomBarVisibilityState(
    private val defaultVisibility: Boolean = true
) : BottomBarVisibilityState {
    private val _isVisible = mutableStateOf(defaultVisibility)
    override val isVisible: Boolean by _isVisible

    override fun hide() {
        _isVisible.value = false
    }

    override fun show() {
        _isVisible.value = true
    }

    companion object {
        val Saver: Saver<DefaultBottomBarVisibilityState, *> = listSaver(
            save = { listOf(it.defaultVisibility) },
            restore = {
                DefaultBottomBarVisibilityState(defaultVisibility = it[0])
            }
        )
    }
}

@Stable
interface BottomBarVisibility {
    val bottomBar: BottomBarVisibilityState
}

@Composable
fun rememberBottomBarVisibility(): BottomBarVisibility {
    val bottomBarState = rememberSaveable(saver = DefaultBottomBarVisibilityState.Saver) {
        DefaultBottomBarVisibilityState()
    }

    val bottomBarVisibility: BottomBarVisibility by remember {
        derivedStateOf {
            object : BottomBarVisibility {
                override val bottomBar: BottomBarVisibilityState
                    get() = bottomBarState
            }
        }
    }
    return bottomBarVisibility
}

val BottomBarHeight = 80.dp
