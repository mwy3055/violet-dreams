package com.mwy3055.violetdreams

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mwy3055.violetdreams.core.theme.VioletDreamsTheme

@Composable
private fun VPracticeCanvas() {
    Canvas(
        modifier = Modifier
            .size(width = 100.dp, height = 500.dp)
            .padding(20.dp),
        onDraw = {
            /**
             * Draw here which you want to practice
             */
            drawPath(
                path = testPath(size = size),
                color = Color.Black,
                style = Fill
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun VPracticeCanvasPreview() {
    VioletDreamsTheme {
        VPracticeCanvas()
    }
}

private fun testPath(size: Size) = Path().apply {
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