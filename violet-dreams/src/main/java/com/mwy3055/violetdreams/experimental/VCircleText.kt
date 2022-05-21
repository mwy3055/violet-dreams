package com.mwy3055.violetdreams.experimental

import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.text.TextPaint
import android.text.TextUtils
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwy3055.violetdreams.theme.VioletDreamsTheme

@Composable
fun VCircleText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 60.sp,
    textColor: Color = MaterialTheme.colors.onPrimary,
) {
    val textPaint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        textSize = fontSize.value
        color = textColor.toArgb()
        typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)
    }

    Canvas(
        modifier = modifier,
        onDraw = {
            val (width, height) = size
            val radius = (kotlin.math.min(width, height) / 4 * 3) / 2
            val textPath = Path().asAndroidPath().apply {
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

            val rect = Rect()
            textPaint.getTextBounds(text, 0, text.length, rect)
            val textLength = rect.width()

            val circumference = 2 * radius * Math.PI
            val displayText = if (textLength > circumference) {
//              Truncate text if it is too long
                TextUtils.ellipsize(
                    text,
                    TextPaint(textPaint),
                    circumference.toFloat() - 20,
                    TextUtils.TruncateAt.END
                ).toString()
            } else {
                text
            }

            drawIntoCanvas {
                it.nativeCanvas.drawTextOnPath(displayText, textPath, 0f, 0f, textPaint)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun VCircleTextPreview() {
    VioletDreamsTheme {
        VCircleText(
            modifier = Modifier
                .padding(20.dp)
                .size(400.dp),
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            textColor = Color.Black
        )
    }
}