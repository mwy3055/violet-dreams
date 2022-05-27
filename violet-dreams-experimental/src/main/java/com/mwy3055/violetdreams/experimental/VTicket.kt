package com.mwy3055.violetdreams

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwy3055.violetdreams.core.shape.VTicketShape
import com.mwy3055.violetdreams.core.shape.ticketPath
import com.mwy3055.violetdreams.core.theme.Teal200
import com.mwy3055.violetdreams.core.theme.VioletDreamsTheme

@Composable
fun VTicket(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = TextStyle(
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Black
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .wrapContentSize()
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = VTicketShape(24.dp.toPx())
                // matches the shadow elevation to custom outline
                clip = true
            }
            .background(color = MaterialTheme.colors.primary)
            .drawBehind {
                scale(scale = 0.9f) {
                    drawPath(
                        path = Path().ticketPath(size = size, cornerRadius = 24.dp.toPx()),
                        color = Teal200,
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                        )
                    )
                }
            }
            .padding(start = 32.dp, top = 64.dp, end = 32.dp, bottom = 64.dp)
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun VTicketPreview() {
    VioletDreamsTheme {
        VTicket(
            text = "My Ticket!",
            modifier = Modifier.padding(20.dp)
        )
    }
}