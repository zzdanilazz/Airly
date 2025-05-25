package ru.health.featuredashboard.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import ru.health.core.presentation.ui.button.TransparentIconButton
import ru.health.core.presentation.ui.theme.AirlyTheme
import ru.health.core.presentation.ui.theme.LightRed
import ru.health.featuredashboard.impl.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DashboardTopBar(
    modifier: Modifier = Modifier,
    name: String = "",
    onProfile: () -> Unit = {},
    onUpload: () -> Unit = {},
    onNotifications: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Color.Transparent
        ),
        title = {},
        actions = {
            Row(
                modifier = Modifier.padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                TransparentIconButton(
                    painter = painterResource(R.drawable.ic_upload),
                    contentDescription = stringResource(R.string.upload),
                    onClick = onUpload
                )
                Spacer(modifier = Modifier.width(10.dp))
                TransparentIconButton(
                    painter = painterResource(R.drawable.ic_notification),
                    contentDescription = stringResource(R.string.notifications),
                    onClick = onNotifications
                ) {
                    if (true) {
                        Badge(containerColor = LightRed)
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
private fun DashboardTopBarPreview(@PreviewParameter(LoremIpsum::class) text: String) {
    AirlyTheme {
        DashboardTopBar(
            name = text.take(11)
        )
    }
}