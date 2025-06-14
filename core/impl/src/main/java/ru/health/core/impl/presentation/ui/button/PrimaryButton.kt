package ru.health.core.impl.presentation.ui.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    buttonText: String,
    @DrawableRes iconResId: Int? = null,
    containerColor: Color = LightRed,
    contentColor: Color = Color.White,
    onButtonClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(50),
        onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors()
            .copy(
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.White.copy(alpha = 0.3f),
                containerColor = containerColor,
                contentColor = contentColor,
            ),
        contentPadding = PaddingValues(16.dp),
        interactionSource = remember { MutableInteractionSource() }
    ) {
        iconResId?.let {
            Icon(
                modifier = Modifier.padding(end = 16.dp),
                painter = painterResource(it),
                contentDescription = null
            )
        }
        Text(
            text = buttonText,
            fontSize = 17.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview(@PreviewParameter(LoremIpsum::class) text: String) {
    AirlyTheme {
        PrimaryButton(modifier = Modifier.fillMaxWidth(), buttonText = text.take(5))
    }
}

@Preview
@Composable
fun PrimaryButtonDisabledPreview(@PreviewParameter(LoremIpsum::class) text: String) {
    AirlyTheme {
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = false,
            buttonText = text.take(5)
        )
    }
}

