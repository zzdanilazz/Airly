package ru.health.featureachievement.impl.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import ru.health.core.impl.presentation.ui._switch.SemitransparentSwitch
import ru.health.core.impl.presentation.ui.field.DefaultTextField
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LocalHazeState
import ru.health.featureachievement.api.presentation.ApproveEventType
import ru.health.featureachievement.api.presentation.ApproveParams
import ru.health.featureachievement.api.presentation.ApproveValues
import ru.health.core.impl.R as CoreR

@Composable
internal fun ApproveContent(
    modifier: Modifier = Modifier,
    params: ApproveParams,
    approveTypeId: Int,
    onApproveEvent: (
        approveTypeId: Int,
        approveEventType: ApproveEventType,
        approveValues: ApproveValues
    ) -> Unit = { _, _, _ -> },
    onDismiss: () -> Unit,
) {
    var selectedIconIndex by remember { mutableIntStateOf(0) }
    var fieldText by remember { mutableStateOf("") }

    val isConfirmButtonEnabled = if (params.fieldTextPlaceholderRes != null || params.fieldTextPlaceholder != null) {
        fieldText.isNotEmpty()
    } else true

    AlertDialog(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.ime)
            .clip(AlertDialogDefaults.shape)
            .hazeEffect(
                state = LocalHazeState.current
            ),
        onDismissRequest = onDismiss,
        containerColor = Color.White.copy(alpha = 0.2f),
        titleContentColor = Color.White,
        textContentColor = Color.White,
        title = {
            Column {
                val text = with(params) { titleRes?.let { stringResource(it) } ?: title }
                text?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                params.switchIconResList?.let { iconList ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        SemitransparentSwitch(
                            items = iconList.mapIndexed { index, iconResId -> index to iconResId },
                            selectedIndex = selectedIconIndex
                        ) {
                            selectedIconIndex = it
                        }
                    }
                }
            }
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val text = with(params) { descriptionRes?.let { stringResource(it) } ?: description }
                text?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                val placeholder = with(params) {
                    fieldTextPlaceholderRes?.let { stringResource(it)
                    } ?: fieldTextPlaceholder
                }
                placeholder?.let {
                    val focusManager = LocalFocusManager.current
                    DefaultTextField(
                        value = fieldText,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        ),
                        placeholder = { Text(text = placeholder) },
                        trailingIcon = params.fieldTrailingText?.let {
                            { Text(text = it) }
                        },
                        onValueChange = { text ->
                            fieldText = text.filter { it.isDigit() }
                        }
                    )
                }
            }

        },
        confirmButton = {
            val text = with(params) { approveButtonTextRes?.let { stringResource(it) } ?: approveButtonText }
            text?.let {
                TextButton(
                    modifier = modifier,
                    enabled = isConfirmButtonEnabled,
                    onClick = {
                        onApproveEvent(
                            approveTypeId,
                            ApproveEventType.APPROVE_BUTTON_CLICK,
                            ApproveValues(
                                fieldText = fieldText,
                                selectedSwitchIndex = selectedIconIndex
                            )
                        )
                        onDismiss()
                    },
                    colors = ButtonDefaults.textButtonColors()
                        .copy(contentColor = MaterialTheme.colorScheme.error),
                    content = {
                        Text(
                            text = it,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                )
            }
        },
        dismissButton = {
            val text = with(params) { declineButtonTextRes?.let { stringResource(it) } ?: declineButtonText }
            text?.let {
                TextButton(
                    modifier = modifier,
                    onClick = {
                        onApproveEvent(
                            approveTypeId,
                            ApproveEventType.DECLINE_BUTTON_CLICK,
                            ApproveValues(
                                fieldText = fieldText,
                                selectedSwitchIndex = selectedIconIndex
                            )
                        )
                        onDismiss()
                    },
                    content = {
                        Text(
                            text = it,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                )
            }
        },
        tonalElevation = 0.dp
    )
}

@Composable
@PreviewLightDark
private fun ApproveContentPreview() {
    AirlyTheme {
        GradientBox(modifier = Modifier.hazeSource(LocalHazeState.current))
        ApproveContent(
            params = ApproveParams(
                titleRes = CoreR.string.disposable,
                descriptionRes = CoreR.string.disposable,
                approveButtonTextRes = CoreR.string.ok,
                declineButtonTextRes = CoreR.string.back,
                fieldTextPlaceholderRes = CoreR.string.flacon_volume,
                switchIconResList = listOf(
                    CoreR.drawable.ic_small_flacon,
                    CoreR.drawable.ic_tall_flacon,
                    CoreR.drawable.ic_large_flacon
                )
            ),
            approveTypeId = 1,
            onDismiss = {}
        )
    }
}