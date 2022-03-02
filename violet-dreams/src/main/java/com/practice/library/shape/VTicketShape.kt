package com.practice.library.shape

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection


class VTicketShape(private val cornerRadius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawTicketPath(size = size, cornerRadius = cornerRadius)
        )
    }
}

/**
 * Draws a ticket shape, starting from top left and turning clockwise
 * arcTo() draws a corner, lineTo() draws a straight border
 */
fun drawTicketPath(size: Size, cornerRadius: Float): Path = Path().apply {
    reset()
    // Top left
    arcTo(
        rect = Rect(
            left = -cornerRadius,
            top = -cornerRadius,
            right = cornerRadius,
            bottom = cornerRadius
        ),
        startAngleDegrees = 90f,
        sweepAngleDegrees = -90f,
        forceMoveTo = false
    )
    lineTo(x = size.width - cornerRadius, y = 0f)
    // Top right
    arcTo(
        rect = Rect(
            left = size.width - cornerRadius,
            top = -cornerRadius,
            right = size.width + cornerRadius,
            bottom = cornerRadius
        ),
        startAngleDegrees = 180f,
        sweepAngleDegrees = -90f,
        forceMoveTo = false
    )
    lineTo(x = size.width, y = size.height - cornerRadius)
    // Bottom right
    arcTo(
        rect = Rect(
            left = size.width - cornerRadius,
            top = size.height - cornerRadius,
            right = size.width + cornerRadius,
            bottom = size.height + cornerRadius
        ),
        startAngleDegrees = 270f,
        sweepAngleDegrees = -90f,
        forceMoveTo = false
    )
    lineTo(x = cornerRadius, y = size.height)
    // Bottom left
    arcTo(
        rect = Rect(
            left = -cornerRadius,
            top = size.height - cornerRadius,
            right = cornerRadius,
            bottom = size.height + cornerRadius
        ),
        startAngleDegrees = 0f,
        sweepAngleDegrees = -90f,
        forceMoveTo = false
    )
    lineTo(x = 0f, y = cornerRadius)
    close()

}