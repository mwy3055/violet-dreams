package com.practice.library.experimental

import android.graphics.DashPathEffect
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.practice.library.shape.polygonOutlineLength
import com.practice.library.shape.polygonPath
import com.practice.library.theme.VioletDreamsTheme

@Composable
fun VPolygon(
    sides: Int,
    radius: Float,
    modifier: Modifier = Modifier,
    dashPathEffect: DashPathEffect? = null,
    polygonPadding: Dp = 0.dp,
) {
    val primaryColor = MaterialTheme.colors.primary.toArgb()

    Canvas(
        modifier = modifier
            .size(radius.dp)
            .padding(polygonPadding),
        onDraw = {
            val polygonPath = Path().polygonPath(sides, radius, size).asAndroidPath()
            val paint = Paint().asFrameworkPaint().apply {
                color = primaryColor
                style = android.graphics.Paint.Style.STROKE
                pathEffect = dashPathEffect
            }
            drawIntoCanvas {
                it.nativeCanvas.drawPath(polygonPath, paint)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun VPolygonPreview() {
    val sides = 6
    val radius = 100f
    val polygonPadding = 10.dp

    // Animate outline
    var ratio by remember { mutableStateOf(0f) }

    val outlineLength = polygonOutlineLength(sides, radius)
    val dashPathEffect = DashPathEffect(
        floatArrayOf(outlineLength * ratio, outlineLength * (1 - ratio)),
        0f
    )

    LaunchedEffect(key1 = true) {
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 5000,
                easing = LinearEasing,
            )
        ) { value, velocity ->
            ratio = value
        }
    }

    VioletDreamsTheme {
        VPolygon(
            sides = sides,
            radius = radius,
            dashPathEffect = dashPathEffect,
            polygonPadding = polygonPadding
        )
    }
}