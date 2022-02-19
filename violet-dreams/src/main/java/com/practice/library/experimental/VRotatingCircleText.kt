package com.practice.library.experimental

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.library.theme.VioletDreamsTheme

@Composable
fun VRotatingCircleText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 60.sp,
    textColor: Color = MaterialTheme.colors.onPrimary,
    durationMillis: Int = 2000,
    easing: Easing = CubicBezierEasing(0.8f, 0.1f, 0.2f, 0.9f),
) {
    val angleAnim = rememberInfiniteTransition()
    val angle by angleAnim.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = durationMillis,
                easing = easing,
            )
        )
    )

    VCircleText(
        text = text,
        modifier = modifier.rotate(angle),
        fontSize = fontSize,
        textColor = textColor
    )
}

@Preview(showBackground = true)
@Composable
private fun VRotatingCircleTextPreview() {
    VioletDreamsTheme {
        VRotatingCircleText(
            modifier = Modifier
                .padding(20.dp)
                .size(400.dp),
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            textColor = Color.Black
        )
    }
}