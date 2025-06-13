package ru.health.core.impl.presentation.ui.field

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import ru.health.core.impl.presentation.ui.theme.AirlyTheme

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    value: String,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (value: String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        placeholder = placeholder,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        shape = RoundedCornerShape(50),
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.colors().copy(
            unfocusedContainerColor = Color.White.copy(0.3f),
            focusedContainerColor = Color.White.copy(0.5f),
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,
            cursorColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedTrailingIconColor = Color.White,
            focusedTrailingIconColor = Color.White,
            unfocusedPlaceholderColor = Color.White.copy(0.3f),
            focusedPlaceholderColor = Color.White.copy(0.5f),
        )
    )
}

@Composable
@Preview
private fun DefaultTextFieldPreview() {
    AirlyTheme {
        DefaultTextField(value = "", onValueChange = {})
    }
}

@Composable
@Preview
private fun DefaultTextFieldFilledPreview(@PreviewParameter(LoremIpsum::class) text: String) {
    AirlyTheme {
        DefaultTextField(value = text.take(11), onValueChange = {})
    }
}