package ru.health.featureliquid.impl.presentation.input.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.core.impl.presentation.ui.theme.RubikOneFamily
import ru.health.featureliquid.impl.R

@Composable
internal fun LiquidSlider(
    modifier: Modifier = Modifier,
    editedVolume: Float,
    currentVolume: Float,
    onVolumeChange: (volume: Float) -> Unit = {}
) {
    val difference = currentVolume - editedVolume
    val textColor by animateColorAsState(
        if (difference > 0) LightRed else Color.White
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.ml_with_prefix, difference),
            fontSize = 24.sp,
            fontFamily = RubikOneFamily,
            textAlign = TextAlign.Center,
            color = textColor
        )
        Spacer(modifier = Modifier.height(8.dp))
        Slider(
            modifier = sliderModifier,
            value = editedVolume,
            onValueChange = {
                onVolumeChange(it)
            },
            colors = SliderDefaults.colors().copy(
                thumbColor = LightRed,
                activeTrackColor = Color.White,
                activeTickColor = LightRed,
                inactiveTrackColor = Color.White.copy(alpha = 0.3f),
                inactiveTickColor = LightRed,
            ),
            valueRange = 0f..currentVolume
        )
    }
}

private val sliderModifier = Modifier
    .graphicsLayer {
        rotationZ = 270f
        transformOrigin = TransformOrigin(0f, 0f)
    }
    .layout { measurable, constraints ->
        val placeable = measurable.measure(
            Constraints(
                minWidth = constraints.minHeight,
                maxWidth = constraints.maxHeight,
                minHeight = constraints.minWidth,
                maxHeight = constraints.maxHeight,
            )
        )
        layout(placeable.height, placeable.width) {
            placeable.place(-placeable.width, 0)
        }
    }
    .fillMaxWidth(0.5f)
    .height(200.dp)

@Composable
@Preview
private fun LiquidSliderPreview() {
    AirlyTheme {
        LiquidSlider(
            editedVolume = 10f,
            currentVolume = 30f
        )
    }
}