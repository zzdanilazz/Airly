package ru.health.airly.tab.impl.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.core.presentation.ui.theme.MostlyDesaturatedDarkBlue

@Composable
internal fun RowScope.BottomTabItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    painter: Painter,
    contentDescription: String? = null,
    onClick: () -> Unit = {}
) {
    val alpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else .35f
    )
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1f else .98f,
        visibilityThreshold = .000001f,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy,
        ),
        label = "scale"
    )

    Column(
        modifier = modifier
            .scale(scale)
            .alpha(alpha)
            .defaultMinSize(minHeight = 50.dp)
            .weight(1f)
            .pointerInput(Unit) {
                detectTapGestures {
                    onClick()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painter,
            tint = if (isSelected) Color.White else MostlyDesaturatedDarkBlue,
            contentDescription = contentDescription,
        )
    }
}

@Composable
@PreviewLightDark
private fun BottomTabItemSelectedPreview () {
    AirlyTheme {
        Row {
            BottomTabItem(
                isSelected = true,
                painter = rememberVectorPainter(Icons.Filled.Dashboard)
            )
        }
    }
}

@Composable
@PreviewLightDark
private fun BottomTabItemUnselectedPreview () {
    AirlyTheme {
        Row {
            BottomTabItem(
                isSelected = false,
                painter = rememberVectorPainter(Icons.Filled.Dashboard)
            )
        }
    }
}