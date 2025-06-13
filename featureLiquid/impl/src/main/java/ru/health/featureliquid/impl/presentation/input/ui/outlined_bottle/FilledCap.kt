package ru.health.featureliquid.impl.presentation.input.ui.outlined_bottle

import android.util.TypedValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.health.core.api.domain.BottleType
import ru.health.featureliquid.impl.R

@Composable
internal fun FilledCap(
    modifier: Modifier = Modifier,
    bottleType: BottleType
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier.size(bottleType.upperCapSize),
            painter = painterResource(R.drawable.illustration_cap),
            colorFilter = ColorFilter.tint(Color.White),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Spacer(
            modifier = Modifier
                .offset(y = (-5).dp)
                .size(bottleType.bottomCapSize)
                .background(Color.White, RoundedCornerShape(5.dp))
        )
    }
}

@Composable
fun mmToDp(mm: Float): Dp {
    val context = LocalContext.current
    val density = LocalDensity.current
    val metrics = context.resources.displayMetrics
    return with (density) {
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mm, metrics).toDp()
    }
}

private val BottleType.upperCapSize: DpSize
    @Composable
    get() = DpSize(width = this.flaconSize.width, height = mmToDp(15f))

private val BottleType.bottomCapSize: DpSize
    @Composable
    get() = DpSize(width = this.flaconSize.width, height = mmToDp(20f))