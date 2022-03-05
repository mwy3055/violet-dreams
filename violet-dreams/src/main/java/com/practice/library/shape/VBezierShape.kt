package com.practice.library.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.max

class VBezierShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().bezierPath(size = size))
    }
}

fun Path.bezierPath(size: Size): Path = this.apply {
    val curveWidth = max(size.width / 10f, 50f)
    val cubicPointWidth = max(curveWidth, 60f)
    reset()
    moveTo(curveWidth, 0f)
    // Starting from top left corner, turning to clockwise direction
    relativeLineTo(size.width - 2 * curveWidth, 0f)
    relativeCubicTo(
        dx1 = cubicPointWidth, dy1 = 0f,
        dx2 = cubicPointWidth, dy2 = size.height,
        dx3 = 0f, dy3 = size.height
    )
    relativeLineTo(-(size.width - 2 * curveWidth), 0f)
    relativeCubicTo(
        dx1 = -cubicPointWidth, dy1 = 0f,
        dx2 = -cubicPointWidth, dy2 = -size.height,
        dx3 = 0f, dy3 = -size.height
    )
    close()
}