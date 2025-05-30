package ru.health.featureliquid.impl.presentation.detail.ui.fab

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.featureliquid.impl.R
import ru.health.featureliquid.impl.presentation.detail.LiquidDetailAction

@Composable
internal fun LiquidDetailFabGroup(
    modifier: Modifier = Modifier,
    isPodSelected: Boolean,
    onAction: (LiquidDetailAction) -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        LiquidDetailFab(
            containerColor = LightRed,
            contentColor = Color.White,
            painter = painterResource(R.drawable.ic_level),
            textResId = R.string.level_fab,
            onClick = { onAction(LiquidDetailAction.EditLiquidLevelApprove) }
        )
        LiquidDetailFab(
            containerColor = LightRed,
            contentColor = Color.White,
            painter = rememberVectorPainter(Icons.Rounded.Add),
            textResId = R.string.liquid_fab,
            onClick = { onAction(LiquidDetailAction.AddLiquidBottle) }
        )
        LiquidDetailFab(
            containerColor = Color.White,
            contentColor = LightRed,
            painter = rememberVectorPainter(Icons.Rounded.Add),
            textResId = R.string.atomizer_fab,
            onClick = { onAction(LiquidDetailAction.AddAtomizer) }
        )
    }
}

@Composable
private fun LiquidDetailFab(
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    painter: Painter,
    @StringRes textResId: Int,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        containerColor = containerColor,
        contentColor = contentColor,
    ) {
        Icon(
            painter = painter,
            contentDescription = stringResource(textResId)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(textResId),
            fontSize = 17.sp
        )
    }
}

@Composable
@Preview
private fun LiquidDetailFabGroupPodSelectedPreview() {
    LiquidDetailFabGroup(isPodSelected = true)
}

@Composable
@Preview
private fun LiquidDetailFabGroupDisposableSelectedPreview() {
    LiquidDetailFabGroup(isPodSelected = false)
}