package ru.health.featureliquid.impl.presentation.detail.ui.disposable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.health.core.impl.presentation.ui.gradient.GradientBox
import ru.health.core.impl.presentation.ui.theme.AirlyTheme

@Composable
internal fun Disposable(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DisposableUpper()
        DisposableBody()
        DisposableBottom()
        Spacer(modifier = Modifier.height(35.dp))
    }
}

internal val DisposableBodyWidth = 78.dp

@PreviewLightDark
@Composable
private fun DisposablePreview() {
    AirlyTheme {
        GradientBox {
            Disposable(modifier = Modifier.align(Alignment.Center))
        }
    }
}