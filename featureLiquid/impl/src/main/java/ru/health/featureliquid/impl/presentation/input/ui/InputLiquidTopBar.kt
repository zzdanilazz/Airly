package ru.health.featureliquid.impl.presentation.input.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import ru.health.core.impl.presentation.ui.button.TransparentIconButton
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LocalHazeState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun InputLiquidTopBar(
    modifier: Modifier = Modifier,
    hazeState: HazeState = LocalHazeState.current,
    onBack: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier.hazeEffect(hazeState),
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Color.Transparent
        ),
        title = {},
        navigationIcon = {
            TransparentIconButton(
                modifier = Modifier.padding(horizontal = 8.dp),
                painter = rememberVectorPainter(Icons.AutoMirrored.Default.ArrowBack),
                onClick = onBack
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
private fun InputLiquidTopBarPreview() {
    AirlyTheme {
        GradientBox(blurred = true)
        InputLiquidTopBar()
    }
}