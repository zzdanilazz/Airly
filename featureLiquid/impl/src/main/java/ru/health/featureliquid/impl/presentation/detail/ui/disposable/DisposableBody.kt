package ru.health.featureliquid.impl.presentation.detail.ui.disposable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.core.impl.presentation.ui.theme.PaleRed

@Composable
internal fun DisposableBody(modifier: Modifier = Modifier) {
    val brush = Brush.horizontalGradient(
        listOf(LightRed, PaleRed)
    )
    Spacer(
        modifier = modifier
            .size(width = DisposableBodyWidth, height = 363.dp)
            .background(brush)
    )
}