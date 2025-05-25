package ru.health.airly.tab.impl.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.health.core.presentation.ui.theme.AirlyTheme

@Composable
internal fun RowScope.BottomTabItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    painter: Painter,
    contentDescription: String? = null,
    onClick: () -> Unit = {}
) {
    NavigationBarItem(
        modifier = modifier,
        selected = isSelected,
        onClick = onClick,
        alwaysShowLabel = false,
        colors = NavigationBarItemDefaults.colors().copy(
            selectedIconColor = Color.White,
            selectedIndicatorColor = Color.White.copy(alpha = 0.3f),
            unselectedIconColor = Color.White,
        ),
        icon = {
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painter,
                contentDescription = contentDescription,
            )
        }
    )
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