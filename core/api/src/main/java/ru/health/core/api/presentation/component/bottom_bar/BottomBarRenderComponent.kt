package ru.health.core.api.presentation.component.bottom_bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface BottomBarRenderComponent {

    @Composable
    fun BottomBarRender(modifier: Modifier, bottomBarVisibility: BottomBarVisibility)
}
