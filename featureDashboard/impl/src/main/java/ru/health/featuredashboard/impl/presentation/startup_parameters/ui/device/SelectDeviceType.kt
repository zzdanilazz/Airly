package ru.health.featuredashboard.impl.presentation.startup_parameters.ui.device

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.api.domain.DeviceType
import ru.health.core.impl.presentation.iconResId
import ru.health.core.impl.presentation.titleResId
import ru.health.core.impl.presentation.ui.button.PrimaryButton
import ru.health.core.impl.presentation.ui.card.GlassmorphismCard
import ru.health.core.impl.presentation.ui.card.GlassmorphismCardShape
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.featuredashboard.impl.R
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersAction
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersUiState
import ru.health.featuredashboard.impl.presentation.startup_parameters.ui.startupParametersUiStatePreview

@Composable
internal fun SelectDeviceType(
    modifier: Modifier = Modifier,
    state: StartupParametersUiState,
    onAction: (action: StartupParametersAction) -> Unit = {},
    onProceed: (deviceType: DeviceType) -> Unit = {}
) {
    val selectedDeviceType = state.deviceType

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DeviceType.entries.filter { it.isPrimary }.forEach {
                    val scale by animateFloatAsState(if (selectedDeviceType == it) 1.1f else 1f)
                    val containerColor by animateColorAsState(
                        if (selectedDeviceType == it) Color.White else Color.Transparent
                    )
                    val contentColor by animateColorAsState(
                        if (selectedDeviceType == it) LightRed else Color.White
                    )

                    GlassmorphismCard(
                        modifier = Modifier
                            .weight(1f)
                            .background(containerColor, GlassmorphismCardShape)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onAction(StartupParametersAction.SelectDeviceType(it))
                                }
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                modifier = Modifier.size(50.dp * scale),
                                painter = painterResource(it.iconResId),
                                tint = contentColor,
                                contentDescription = null,
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(it.titleResId),
                                fontSize = 16.sp * scale,
                                color = contentColor
                            )
                        }
                    }
                }
            }
        }
        PrimaryButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            isEnabled = selectedDeviceType != null,
            buttonText = stringResource(R.string.proceed),
            onButtonClick = {
                selectedDeviceType?.let { onProceed(it) }
            }
        )
    }
}

@PreviewLightDark
@Composable
private fun SelectDeviceTypePreview() {
    AirlyTheme {
        GradientBox {
            SelectDeviceType(state = startupParametersUiStatePreview)
        }
    }
}

@PreviewLightDark
@Composable
private fun SelectDeviceTypeIsSelectedPreview() {
    AirlyTheme {
        GradientBox {
            SelectDeviceType(state = startupParametersUiStatePreview.copy(
                deviceType = DeviceType.POD,
            ))
        }
    }
}