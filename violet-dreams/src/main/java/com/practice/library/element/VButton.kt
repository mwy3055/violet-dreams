package com.practice.library.element

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.library.shape.VBezierShape
import com.practice.library.theme.VioletDreamsTheme

@Composable
fun VButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Text(
        text = text,
        style = TextStyle(
            color = MaterialTheme.colors.onPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .wrapContentSize()
            .graphicsLayer {
                shape = VBezierShape()
                clip = true
            }
            .background(color = MaterialTheme.colors.primary)
            .clickable(onClick = onClick)
            .padding(horizontal = 25.dp, vertical = 10.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun VButtonPreview() {
    VioletDreamsTheme {
        VButton(
            text = "I want to go home",
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VButtonPreviewShortText() {
    VioletDreamsTheme {
        VButton(
            text = "확인",
            modifier = Modifier.padding(10.dp)
        )
    }
}