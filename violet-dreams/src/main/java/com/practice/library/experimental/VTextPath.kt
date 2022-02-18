package com.practice.library.experimental

import android.graphics.RectF
import android.graphics.Typeface
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.library.theme.VioletDreamsTheme

@Composable
fun VTextPath(
    modifier: Modifier = Modifier,
    text: String
) {
    val textPaint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        textSize = 60.sp.value
        color = android.graphics.Color.BLACK
        typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)
    }

    val angleAnim = rememberInfiniteTransition()
    val angle by angleAnim.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 2000,
                easing = CubicBezierEasing(0.8f, 0.1f, 0.2f, 0.9f)
            )
        )
    )
    Canvas(modifier = modifier.size(400.dp),
        onDraw = {
            val (width, height) = size
            val textPath = Path().asAndroidPath().apply {
                addArc(
                    RectF(width / 8 * 1, height / 8 * 1, width / 8 * 7, height / 8 * 7),
                    270f,
                    360f
                )
            }
            translate {
                rotate(degrees = angle) {
                    drawIntoCanvas {
                        it.nativeCanvas.drawTextOnPath(text, textPath, 0f, 0f, textPaint)
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun VTextPathPreview() {
    VioletDreamsTheme {
        VTextPath(
            modifier = Modifier.padding(20.dp),
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )
    }
}