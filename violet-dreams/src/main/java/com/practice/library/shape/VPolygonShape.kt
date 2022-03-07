package com.practice.library.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Regular polygon.
 */
class VPolygonShape(val sides: Int, val radius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().polygonPath(sides, radius, size))
    }
}

fun Path.polygonPath(sides: Int, radius: Float, size: Size) = this.apply {
    val centerX = size.width / 2
    val centerY = size.height / 2
    val angle = 2 * Math.PI / sides
    reset()
    moveTo(x = centerX + radius, y = centerY)
    (1 until sides).forEach { i ->
        lineTo(
            x = centerX + (radius * cos(i * angle)).toFloat(),
            y = centerY + (radius * sin(i * angle)).toFloat()
        )
    }
    close()
}

fun polygonOutlineLength(sides: Int, radius: Float): Float {
    val sideLength = 2 * radius * sin(PI / sides).toFloat()
    return sides * sideLength
}