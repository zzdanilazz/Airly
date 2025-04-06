package ru.health.airly.tab.impl.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.featuredashboard.impl.R as DashboardR

@Composable
internal fun RowScope.BottomTabItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    @StringRes labelRes: Int,
    imageVector: ImageVector,
    onClick: () -> Unit = {}
) {
    NavigationBarItem(
        modifier = modifier,
        selected = isSelected,
        onClick = onClick,
        alwaysShowLabel = true,
        label = {
            Text(
                text = stringResource(id = labelRes),
                color = MaterialTheme.colorScheme.primary
            )
        },
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = imageVector,
                contentDescription = stringResource(id = labelRes),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    )
}

@Composable
@PreviewLightDark
private fun BottomTabItemSelectedPreview () {
    AirlyTheme {
        Row(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            BottomTabItem(
                isSelected = true,
                labelRes = DashboardR.string.dashboard,
                imageVector = Icons.Filled.Dashboard
            )
        }
    }
}

@Composable
@PreviewLightDark
private fun BottomTabItemUnselectedPreview () {
    AirlyTheme {
        Row(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            BottomTabItem(
                isSelected = false,
                labelRes = DashboardR.string.dashboard,
                imageVector = Icons.Filled.Dashboard
            )
        }
    }
}