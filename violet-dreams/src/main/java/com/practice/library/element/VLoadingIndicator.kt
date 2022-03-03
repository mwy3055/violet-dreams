package com.practice.library.element

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practice.library.theme.VioletDreamsTheme
import kotlin.math.max

@Composable
fun VLoadingIndicator(
    modifier: Modifier = Modifier,
) {
    val primaryColor = MaterialTheme.colors.primary
    val backgroundColor = Color(0xBB000000)

    val infiniteTransition = rememberInfiniteTransition()
    val x by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                easing = CubicBezierEasing(0.5f, 0.0f, 0.5f, 1f)
            ),
            repeatMode = RepeatMode.Reverse,
        )
    )

    Canvas(modifier = modifier,
        onDraw = {
            drawRect(
                color = backgroundColor,
                alpha = 0.5f,
            )

            val (width, height) = size
            val barWidth = max(100f, width / 4)
            val barHeight = barWidth / 5

            val barLeft = width / 2 - barWidth / 2
            val barTop = height / 2 - barHeight / 2
            val barCornerRadius = barHeight / 2
            drawRoundRect(
                color = Color(0xFFFFFFFF),
                topLeft = Offset(barLeft, barTop),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(barCornerRadius),
            )

            val radius = barCornerRadius * 7 / 10
            val effectiveBarWidth = barWidth - (barCornerRadius * 2)
            drawCircle(
                color = primaryColor,
                radius = radius,
                center = Offset(barLeft + barCornerRadius + x * effectiveBarWidth, height / 2),
            )
        }
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun VLoadingIndicatorPreview() {
    VioletDreamsTheme {
        VLoadingIndicator(
            modifier = Modifier
                .padding(20.dp)
                .size(1080.dp, 1920.dp)
        )
    }
}