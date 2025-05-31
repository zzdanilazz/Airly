package ru.health.featureliquid.impl.presentation.detail.ui.disposable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.theme.LightGrayishYellow
import ru.health.core.impl.presentation.ui.theme.VeryDarkDesaturatedOrange
import ru.health.core.impl.presentation.ui.theme.VeryDarkGray

@Composable
internal fun DisposableUpper(modifier: Modifier = Modifier) {
    val darkBrush = Brush.horizontalGradient(
        listOf(Color.Black, VeryDarkGray)
    )
    val lightBrush = Brush.horizontalGradient(
        listOf(VeryDarkDesaturatedOrange, LightGrayishYellow)
    )
    val shape = RoundedCornerShape(8.dp, 8.dp)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .size(width = 47.dp, height = 52.dp)
                .background(darkBrush, shape)
        )
        Spacer(
            modifier = Modifier
                .size(width = DisposableBodyWidth, height = 15.dp)
                .background(darkBrush, shape)
        )
        Spacer(
            modifier = Modifier
                .size(width = DisposableBodyWidth, height = 10.dp)
                .background(lightBrush)
        )
    }
}