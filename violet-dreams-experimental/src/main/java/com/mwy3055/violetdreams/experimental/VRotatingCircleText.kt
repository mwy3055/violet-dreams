package com.mwy3055.violetdreams.experimental

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwy3055.violetdreams.core.theme.VioletDreamsTheme

@Composable
fun VRotatingCircleText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 60.sp,
    textColor: Color = MaterialTheme.colors.onPrimary,
) {
    VRotatingPane(modifier = modifier) {
        VCircleText(
            text = text,
            fontSize = fontSize,
            textColor = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VRotatingCircleTextPreview() {
    VioletDreamsTheme {
        VRotatingCircleText(
            modifier = Modifier
                .padding(20.dp)
                .size(400.dp),
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            textColor = Color.Black
        )
    }
}