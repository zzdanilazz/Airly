package ru.health.core.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.rememberHazeState

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xff3ee92f),
    onPrimary = Color(0xff013a00),

    secondary = Color(0xff81dc6d),
    onSecondary = Color(0xff013a00),

    tertiary = Color(0xff44e0df),
    onTertiary = Color(0xff003737),

    error = Color(0xffffb4ab),
    onError = Color(0xff690005),

    primaryContainer = Color(0xff00cc00),
    onPrimaryContainer = Color(0xff014f00),

    secondaryContainer = Color(0xff046901),
    onSecondaryContainer = Color(0xff8be776),

    tertiaryContainer = Color(0xff00c4c3),
    onTertiaryContainer = Color(0xff004b4b),

    errorContainer = Color(0xff93000a),
    onErrorContainer = Color(0xffffdad6),

    surfaceDim = Color(0xff0d160a),
    surface = Color(0xff0d160a),
    surfaceBright = Color(0xff323c2e),

    surfaceContainerLowest = Color(0xff081006),
    surfaceContainerLow = Color(0xff151e12),
    surfaceContainer = Color(0xff192216),
    surfaceContainerHigh = Color(0xff232c20),
    surfaceContainerHighest = Color(0xff2e372a),

    onSurface = Color(0xffdbe6d2),
    onSurfaceVariant = Color(0xffbbcbb2),
    outline = Color(0xff86957e),
    outlineVariant = Color(0xff3d4b37),

    inverseSurface = Color(0xffdbe6d2),
    inverseOnSurface = Color(0xff2a3326),
    inversePrimary = Color(0xff016e00),

    scrim = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xff016e00),
    onPrimary = Color.White,

    secondary = Color(0xff0e6e08),
    onSecondary = Color.White,

    tertiary = Color(0xff006a69),
    onTertiary = Color.White,

    error = Color(0xffba1a1a),
    onError = Color.White,

    primaryContainer = Color(0xff00cc00),
    onPrimaryContainer = Color(0xff014f00),

    secondaryContainer = Color(0xff9cf986),
    onSecondaryContainer = Color(0xff187410),

    tertiaryContainer = Color(0xff00c4c3),
    onTertiaryContainer = Color(0xff004b4b),

    errorContainer = Color(0xffffdad6),
    onErrorContainer = Color(0xff93000a),

    surfaceDim = Color(0xffd3ddca),
    surface = Color(0xfff2fde9),
    surfaceBright = Color(0xfff2fde9),

    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xffecf7e3),
    surfaceContainer = Color(0xffe7f1dd),
    surfaceContainerHigh = Color(0xffe1ecd8),
    surfaceContainerHighest = Color(0xffdbe6d2),

    onSurface = Color(0xff151e12),
    onSurfaceVariant = Color(0xff3d4b37),
    outline = Color(0xff6c7b65),
    outlineVariant = Color(0xffbbcbb2),

    inverseSurface = Color(0xff2a3326),
    inverseOnSurface = Color(0xffe9f4e0),
    inversePrimary = Color(0xff37e429),

    scrim = Color.Black
)

val LocalHazeState = compositionLocalOf { HazeState() }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirlyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val hazeState = rememberHazeState()

    CompositionLocalProvider(LocalHazeState provides hazeState) {
        MaterialTheme {
            content()
        }
    }
}