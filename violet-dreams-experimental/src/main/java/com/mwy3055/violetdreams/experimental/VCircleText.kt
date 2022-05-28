package com.mwy3055.violetdreams.experimental

import android.graphics.*
import android.text.TextPaint
import android.text.TextUtils
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwy3055.violetdreams.core.theme.VioletDreamsTheme

@Composable
fun VCircleText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 60.sp,
    textColor: Color = MaterialTheme.colors.onBackground,
) {
    val textPaint = createTextPaint(fontSize, textColor)

    Canvas(
        modifier = modifier.fillMaxSize(),
        onDraw = {
            val radius = calculateRadius(size, fontSize)
            val displayText = truncateText(text, radius, textPaint)

            val textPath = getAndroidCirclePath(size, radius)
            drawIntoCanvas {
                it.nativeCanvas.drawTextOnPath(displayText, textPath, 0f, 0f, textPaint)
            }
        }
    )
}

@Composable
private fun createTextPaint(fontSize: TextUnit, textColor: Color) = Paint().apply {
    isAntiAlias = true
    textSize = fontSize.value
    color = textColor.toArgb()
    typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)
}

private fun getAndroidCirclePath(size: Size, radius: Float): Path {
    val (width, height) = size
    return Path().apply {
        addArc(
            RectF(
                width / 2 - radius,
                height / 2 - radius,
                width / 2 + radius,
                height / 2 + radius
            ),
            270f,
            360f
        )
    }
}

private fun calculateRadius(size: Size, fontSize: TextUnit): Float =
    (size.minDimension - fontSize.value) / 2

private fun truncateText(text: String, radius: Float, textPaint: NativePaint): String {
    val textWidth = getTextWidth(text, textPaint)

    val circumference = 2 * radius * Math.PI
    return if (textWidth > circumference) {
        TextUtils.ellipsize(
            text,
            TextPaint(textPaint),
            circumference.toFloat() - textPaint.textSize / 3,
            TextUtils.TruncateAt.END
        ).toString()
    } else {
        text
    }
}

private fun getTextWidth(text: String, textPaint: NativePaint): Int {
    val rect = Rect()
    textPaint.getTextBounds(text, 0, text.length, rect)
    return rect.width()
}

@Preview(showBackground = true)
@Composable
private fun VCircleTextPreview() {
    VioletDreamsTheme {
        VCircleText(
            modifier = Modifier
                .padding(10.dp)
                .size(400.dp),
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VCircleTextPreview_Short() {
    VioletDreamsTheme {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(200.dp)
        ) {
            VCircleText(
                text = "short",
            )
        }
    }
}