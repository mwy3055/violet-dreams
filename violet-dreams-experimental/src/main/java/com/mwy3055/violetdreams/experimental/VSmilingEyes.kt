package com.mwy3055.violetdreams.experimental

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mwy3055.violetdreams.core.theme.VioletDreamsTheme

@Composable
fun VSmilingEyes(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier.size(400.dp),
        onDraw = {
            val (width, height) = size
            drawCircle(
                color = Color.Black,
                radius = width / 2,
                center = center,
                style = Stroke(width = width * 0.02f)
            )

            val leftEyePath = Path().apply {
                moveTo(width / 16 * 4, height / 16 * 5)
                lineTo(width / 16 * 5, height / 16 * 4)
                lineTo(width / 16 * 6, height / 16 * 5)
            }
            drawPath(
                path = leftEyePath,
                color = Color.Black,
                style = Stroke(width = 20f),
            )

            val rightEyePath = Path().apply {
                moveTo(width / 16 * 10, height / 16 * 5)
                lineTo(width / 16 * 11, height / 16 * 4)
                lineTo(width / 16 * 12, height / 16 * 5)
            }
            drawPath(
                path = rightEyePath,
                color = Color.Black,
                style = Stroke(width = 20f),
            )

            val mouthPadding = size.width * 0.1f
            drawArc(
                color = Color.Red,
                startAngle = 25f,
                sweepAngle = 130f,
                useCenter = false,
                topLeft = Offset(mouthPadding, mouthPadding),
                size = Size(size.width - (mouthPadding * 2f), size.height - (mouthPadding * 3f)),
                style = Stroke(width = 20f)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun VSmilingEyesPreview() {
    VioletDreamsTheme {
        VSmilingEyes(modifier = Modifier.padding(20.dp))
    }
}