package com.practice.library.experimental

import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill

@Composable
fun VStick(modifier: Modifier = Modifier) {
    val color = MaterialTheme.colors.onBackground
    Canvas(
        modifier = modifier,
        onDraw = {
            drawPath(
                path = Path().stickPath(size),
                color = color,
                style = Fill
            )
        }
    )
}

fun Path.stickPath(size: Size): Path = this.apply {
    val triangleHeight = size.height / 5
    reset()
    // Triangle at the top
    moveTo(size.width / 2, 0f)
    relativeLineTo(size.width / 2, triangleHeight)
    relativeLineTo(-size.width, 0f)
    relativeLineTo(size.width / 2, -triangleHeight)
    // Square at the bottom
    addRect(Rect(0f, triangleHeight, size.width, size.height))
}