package ru.health.featuredashboard.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed

@Composable
internal fun AddActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = LightRed,
            contentColor = Color.White,
        ),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            tint = Color.White,
            contentDescription = null
        )
    }
}

@Composable
@Preview
private fun AddActionButtonPreview() {
    AirlyTheme {
        AddActionButton()
    }
}