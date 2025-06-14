package ru.health.featuredashboard.impl.presentation.startup_parameters.ui.consumption

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.api.domain.DeviceType
import ru.health.core.impl.presentation.scroll.scrollToItemOnFocusChange
import ru.health.core.impl.presentation.ui.button.PrimaryButton
import ru.health.core.impl.presentation.ui.field.DefaultTextField
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme
import ru.health.featuredashboard.impl.R
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersAction
import ru.health.featuredashboard.impl.presentation.startup_parameters.StartupParametersUiState
import ru.health.featuredashboard.impl.presentation.startup_parameters.ui.startupParametersUiStatePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FillConsumption(
    modifier: Modifier = Modifier,
    state: StartupParametersUiState,
    onAction: (action: StartupParametersAction) -> Unit = {},
    onProceed: () -> Unit = {}
) {
    val listState = rememberLazyListState()
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .navigationBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(16.dp)
                .padding(bottom = 70.dp),
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            @StringRes val devicePriceTitleRes: Int
            @StringRes val deviceBuyPeriodTitleRes: Int
            val devicePlaceHolder: String

            when (state.deviceType) {
                DeviceType.POD -> {
                    devicePriceTitleRes = R.string.price_per_flacon
                    deviceBuyPeriodTitleRes = R.string.flacon_buy_period
                    devicePlaceHolder = "500"
                }
                DeviceType.DISPOSABLE -> {
                    devicePriceTitleRes = R.string.price_per_disposable
                    deviceBuyPeriodTitleRes = R.string.disposable_buy_period
                    devicePlaceHolder = "2000"
                }
                null -> return@LazyColumn
            }
            item {
                Spacer(
                    modifier = Modifier
                        .padding(top = TopAppBarDefaults.MediumAppBarExpandedHeight)
                        .statusBarsPadding()
                )

                NumberTextFieldWithTitle(
                    modifier = Modifier.scrollToItemOnFocusChange(listState, 0),
                    titleRes = devicePriceTitleRes,
                    imeAction = ImeAction.Next,
                    value = state.pricePerDevice,
                    placeholder = devicePlaceHolder,
                    trailingText = "₽",
                    onValueChange = {
                        onAction(StartupParametersAction.ChangeDevicePrice(it))
                    }
                )
            }

            item {
                val deviceBuyPeriodImeAction = when (state.deviceType) {
                    DeviceType.POD -> ImeAction.Next
                    DeviceType.DISPOSABLE -> ImeAction.Done
                }

                NumberTextFieldWithTitle(
                    modifier = Modifier.scrollToItemOnFocusChange(listState, 1),
                    titleRes = deviceBuyPeriodTitleRes,
                    imeAction = deviceBuyPeriodImeAction,
                    value = state.deviceBuyPeriod,
                    placeholder = "14",
                    trailingText = "сут.",
                    onValueChange = {
                        onAction(StartupParametersAction.ChangeDeviceBuyPeriod(it))
                    }
                )
            }

            if (state.deviceType == DeviceType.POD) {
                item {
                    NumberTextFieldWithTitle(
                        modifier = Modifier.scrollToItemOnFocusChange(listState, 2),
                        titleRes = R.string.price_per_vaporizer,
                        imeAction = ImeAction.Next,
                        value = state.pricePerVaporizer,
                        placeholder = "300",
                        trailingText = "₽",
                        onValueChange = {
                            onAction(StartupParametersAction.ChangeVaporizerPrice(it))
                        }
                    )
                }

                item {
                    NumberTextFieldWithTitle(
                        modifier = Modifier.scrollToItemOnFocusChange(listState, 3),
                        titleRes = R.string.vaporizer_buy_period,
                        imeAction = ImeAction.Done,
                        value = state.vaporizerBuyPeriod,
                        placeholder = "21",
                        trailingText = "сут.",
                        onValueChange = {
                            onAction(StartupParametersAction.ChangeVaporizerBuyPeriod(it))
                        }
                    )
                }
            }
        }

        PrimaryButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            isEnabled = state.areFieldsFilled,
            buttonText = stringResource(R.string.proceed),
            onButtonClick = {
                focusManager.clearFocus()
                onProceed()
            }
        )
    }
}

private fun filterValue(value: String): String {
    return value.filterIndexed { index, ch ->
        ch.isDigit() && (index != 0 || ch != '0')
    }
}

@Composable
private fun NumberTextFieldWithTitle(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    imeAction: ImeAction,
    value: String,
    placeholder: String,
    trailingText: String,
    onValueChange: (value: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(titleRes),
            fontSize = 18.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        DefaultTextField(
            value = value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            placeholder = { Text(text = placeholder) },
            trailingIcon = { Text(text = trailingText) },
            onValueChange = {
                onValueChange(filterValue(it))
            }
        )
    }
}

@PreviewLightDark
@Composable
private fun FillConsumptionPreview() {
    AirlyTheme {
        GradientBox {
            FillConsumption(state = startupParametersUiStatePreview)
        }
    }
}

@PreviewLightDark
@Composable
private fun FillConsumptionFilledPreview() {
    AirlyTheme {
        GradientBox {
            FillConsumption(state = startupParametersUiStatePreview.copy(
                pricePerDevice = "100",
                deviceBuyPeriod = "30"
            ))
        }
    }
}