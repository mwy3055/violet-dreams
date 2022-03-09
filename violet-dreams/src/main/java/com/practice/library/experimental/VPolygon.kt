package com.practice.library.experimental

import android.graphics.DashPathEffect
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
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
import kotlin.math.min

@Composable
fun VPolygon(
    sides: Int,
    modifier: Modifier = Modifier,
    lineWidth: Dp? = null,
    interval: FloatArray? = null,
    phase: Float? = null,
    polygonPadding: Dp = 0.dp,
) {
    val primaryColor = MaterialTheme.colors.primary.toArgb()

    Canvas(
        modifier = modifier
            .padding(polygonPadding)
            .wrapContentSize()
            .aspectRatio(1f),
        onDraw = {
            val radius = min(size.width, size.height) / 2

            val polygonPath = Path().polygonPath(sides, radius, size).asAndroidPath()
            val outlineLength = polygonOutlineLength(sides, radius)

            val dashPathEffect = if (!arrayOf(interval, phase).contains(null)) {
                DashPathEffect(
                    interval!!.map { it * outlineLength }.toFloatArray(),
                    phase!!
                )
            } else {
                null
            }

            val paint = Paint().asFrameworkPaint().apply {
                color = primaryColor
                strokeWidth = lineWidth?.value ?: radius / 20f
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
    val polygonPadding = 10.dp

    // Animate outline
    var ratio by remember { mutableStateOf(0f) }

    val interval = floatArrayOf(ratio, 1 - ratio)

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
            modifier = Modifier.size(400.dp),
            interval = interval,
            polygonPadding = polygonPadding
        )
    }
}