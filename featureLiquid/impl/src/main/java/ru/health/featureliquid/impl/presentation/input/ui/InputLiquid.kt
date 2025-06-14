package ru.health.featureliquid.impl.presentation.input.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.health.featureliquid.api.domain.model.FlaconParams
import ru.health.core.api.domain.FlaconType
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.featureliquid.impl.presentation.input.InputLiquidAction
import ru.health.featureliquid.impl.presentation.input.InputLiquidUiState
import ru.health.featureliquid.impl.presentation.input.ui.outlined_bottle.OutlinedBottle

@Composable
internal fun InputLiquid(
    modifier: Modifier = Modifier,
    state: InputLiquidUiState,
    onAction: (action: InputLiquidAction) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val difference = state.flaconParams.volume - state.editedVolume
    val isFabVisible = difference > 0f && (!state.isPositiveVolume || state.editedVolume > 0)

    GradientBox(
        modifier = modifier.fillMaxSize(),
        blurred = true
    )

    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedBottle(
            modifier = Modifier
                .verticalScroll(scrollState)
                .navigationBarsPadding()
                .padding(start = 16.dp),
            flaconParams = state.flaconParams,
            editedVolume = state.editedVolume
        )
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            LiquidSlider(
                modifier = Modifier.align(Alignment.Center),
                editedVolume = state.editedVolume,
                currentVolume = state.flaconParams.volume
            ) {
                onAction(InputLiquidAction.OnVolumeChange(it))
            }
            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                AnimatedVisibility(
                    visible = isFabVisible,
                    enter = scaleIn(),
                    exit = scaleOut()
                ) {
                    FloatingActionButton(
                        modifier = Modifier.navigationBarsPadding(),
                        onClick = { onAction(InputLiquidAction.OnSaveApprove) },
                        containerColor = LightRed,
                        contentColor = Color.White,
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Check,
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}


internal val flaconParamsPreview = FlaconParams(
    volume = 12.3f,
    flaconType = FlaconType.SMALL
)

internal val inputLiquidUiStatePreview = InputLiquidUiState(
    flaconParams = flaconParamsPreview
)

@PreviewLightDark
@Composable
private fun InputLiquidPreview() {
    AirlyTheme {
        GradientBox(blurred = true)
        InputLiquid(
            state = inputLiquidUiStatePreview.copy(
                editedVolume = 10f
            )
        )
    }
}