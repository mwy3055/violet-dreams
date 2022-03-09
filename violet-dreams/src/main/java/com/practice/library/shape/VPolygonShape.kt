package com.practice.library.shape

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
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
    val angle = Math.PI / sides
    // center: (0, 0)
    reset()
    (0 until 2 * sides).map { i ->
        val r = if (i % 2 == 1) radius.toDouble() else radius * cos(angle)
        Offset(
            x = (r * cos(i * angle)).toFloat(),
            y = (r * sin(i * angle)).toFloat()
        )
    }.forEachIndexed { index, offset ->
        if (index == 0) {
            moveTo(offset.x, offset.y)
        } else {
            lineTo(offset.x, offset.y)
        }
    }
    // center: (centerX, centerY)
    translate(Offset(x = size.width / 2, y = size.height / 2))
    close()
}

fun polygonOutlineLength(sides: Int, radius: Float): Float {
    val sideLength = 2 * radius * sin(PI / sides).toFloat()
    return sides * sideLength
}

fun Offset.rotate(angle: Float) = this.copy(
    x = x * cos(angle) - y * sin(angle),
    y = x * sin(angle) + y * cos(angle)
)

fun <T> List<T>.pushLeft(count: Int): List<T> = subList(count, size) + subList(0, count)