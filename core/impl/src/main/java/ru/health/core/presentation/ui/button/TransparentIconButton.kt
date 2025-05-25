package ru.health.core.presentation.ui.button

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.PreviewLightDark
import ru.health.core.presentation.ui.theme.AirlyTheme

@Composable
fun TransparentIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String? = null,
    onClick: () -> Unit = {},
    badge: @Composable (BoxScope.() -> Unit) = {},
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors().copy(
            containerColor = Color.Transparent,
            contentColor = Color.White
        )
    ) {
        BadgedBox(badge = badge) {
            Icon(
                painter = painter,
                contentDescription = contentDescription
            )
        }
    }
}

@Composable
@PreviewLightDark
private fun TransparentIconButtonPreview() {
    AirlyTheme {
        TransparentIconButton(painter = rememberVectorPainter(Icons.Filled.Person))
    }
}

@Composable
@PreviewLightDark
private fun BadgedTransparentIconButtonPreview() {
    AirlyTheme {
        TransparentIconButton(
            painter = rememberVectorPainter(Icons.Filled.Person),
            badge = {
                Badge()
            }
        )
    }
}