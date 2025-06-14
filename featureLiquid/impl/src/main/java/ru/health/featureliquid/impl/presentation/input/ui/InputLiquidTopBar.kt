package ru.health.featureliquid.impl.presentation.input.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import ru.health.core.impl.presentation.ui.button.TransparentIconButton
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.DarkBlue
import ru.health.core.impl.presentation.ui.theme.LocalHazeState
import ru.health.core.impl.presentation.ui.theme.RubikOneFamily
import ru.health.featureliquid.impl.R

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
            containerColor = DarkBlue.copy(alpha = 0.3f)
        ),
        title = {
            Text(
                text = stringResource(R.string.lean_the_flacon_against),
                fontSize = 24.sp,
                fontFamily = RubikOneFamily,
                color = Color.White
            )
        },
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