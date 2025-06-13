package ru.health.featureliquid.impl.presentation.detail.ui.bottle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.health.core.impl.presentation.ui.theme.LightRed
import ru.health.core.impl.presentation.ui.theme.ModerateRed
import ru.health.core.impl.presentation.ui.theme.PaleRed
import ru.health.core.impl.presentation.ui.theme.RubikOneFamily
import ru.health.core.api.domain.BottleType
import ru.health.featureliquid.impl.R
import kotlin.math.roundToInt

@Composable
internal fun Cap(
    modifier: Modifier = Modifier,
    bottleType: BottleType
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier.size(bottleType.upperCapSize),
            painter = painterResource(R.drawable.illustration_cap),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        val brush = Brush.verticalGradient(
            listOf(PaleRed, LightRed)
        )
        Box(
            modifier = Modifier
                .offset(y = (-5).dp)
                .size(bottleType.bottomCapSize)
                .background(brush, RoundedCornerShape(5.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.ml, bottleType.volume.roundToInt()),
                fontFamily = RubikOneFamily,
                fontSize = 24.sp,
                color = ModerateRed
            )
        }
        Spacer(
            modifier = Modifier
                .width(bottleType.bottomCapSize.width)
                .height(16.dp)
                .background(brush, RoundedCornerShape(5.dp))
        )
    }
}

private val BottleType.upperCapSize: DpSize
    get() = DpSize(width = this.flaconSize.width, height = 68.dp)

private val BottleType.bottomCapSize: DpSize
    get() = DpSize(width = this.flaconSize.width, height = 106.dp)