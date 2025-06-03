package ru.health.featureliquid.impl.presentation.detail.ui.bottle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ru.health.core.impl.presentation.ui.theme.RubikOneFamily
import ru.health.core.impl.presentation.ui.theme.VeryDarkGray
import ru.health.featureliquid.impl.R

@Composable
internal fun Liquid(
    modifier: Modifier = Modifier,
    currentVolume: Float,
) {
    val brush = Brush.verticalGradient(
        listOf(VeryDarkGray, Color.Black)
    )
    Box(
        modifier = modifier.background(brush),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.ml, currentVolume),
            fontFamily = RubikOneFamily,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}