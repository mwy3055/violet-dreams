package com.mwy3055.violetdreams.theme

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

private val DarkColorPalette = darkColors(
    primary = Lavender600,
    primaryVariant = Lavender900,
    background = Black900,
    surface = Black700,
    error = Color.Red,
    onPrimary = Color.Black,
    onBackground = Lavender900,
    onSurface = Lavender700,
    onError = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Lavender500,
    primaryVariant = Lavender800,
    background = Color.White,
    surface = Color.White,
    error = Color.Red,
    onPrimary = Color.Black,
    onBackground = Lavender500,
    onSurface = Lavender500,
    onError = Color.White,
)

@Composable
fun VioletDreamsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

//@Preview(showBackground = true)
//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
//annotation class VioletDreamsPreview

@Preview(showBackground = true)
@Composable
private fun VioletDreamsThemePreview() {
    VioletDreamsTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            val colors = listOf(
                MaterialTheme.colors.primary,
                MaterialTheme.colors.primaryVariant,
                MaterialTheme.colors.surface,
            )
            colors.forEach { color ->
                ThemePreviewBox(
                    textColor = contentColorFor(backgroundColor = color),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(color)
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun VioletDreamsThemePreview_Dark() {
    VioletDreamsTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            val colors = listOf(
                MaterialTheme.colors.primary,
                MaterialTheme.colors.primaryVariant,
                MaterialTheme.colors.surface,
            )
            colors.forEach { color ->
                ThemePreviewBox(
                    textColor = contentColorFor(backgroundColor = color),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(color)
                )
            }
        }
    }
}

@Composable
private fun ThemePreviewBox(
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "Violet Dreams",
            color = textColor,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
