package ru.health.core.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface RenderComponent {

    @Composable
    fun Render(modifier: Modifier)
}
