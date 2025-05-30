package ru.health.core.api.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface RenderComponent {

    @Composable
    fun Render(modifier: Modifier)
}
