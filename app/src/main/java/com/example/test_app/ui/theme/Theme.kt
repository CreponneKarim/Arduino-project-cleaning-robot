package com.example.test_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = blue3,
    primaryVariant = blue1,
    secondary = blue2,
    secondaryVariant = blue2,
    background = dark2,
    surface = dark1,
    error = red2,

    onPrimary = white,
    onSecondary = white,
    onBackground = white,
    onSurface = white,
    onError = black
)

private val LightColorPalette = lightColors(
    primary = blue1,
    primaryVariant = blue2,
    secondary = blue3,
    secondaryVariant = blue2,
    background = gray4,
    surface = white,
    error = red1,

    onPrimary = white,
    onSecondary = white,
    onBackground = black,
    onSurface = black,
    onError = white
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}