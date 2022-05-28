package com.mwy3055.violetdreams

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mwy3055.violetdreams.ui.element.VButton
import com.mwy3055.violetdreams.core.theme.VioletDreamsTheme
import com.mwy3055.violetdreams.experimental.VCircleText

@Composable
fun VRotatingPane(
    modifier: Modifier = Modifier,
    rotate: Boolean = true,
    durationMillis: Int = 2000,
    easing: Easing = CubicBezierEasing(0.8f, 0.1f, 0.2f, 0.9f),
    content: @Composable () -> Unit = {},
) {
    val angleAnim = rememberInfiniteTransition()
    val angle by angleAnim.animateFloat(
        initialValue = 0f,
        targetValue = if (rotate) 360f else 0f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = durationMillis,
                easing = easing,
            )
        )
    )

    Box(modifier = modifier.rotate(angle)) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun VRotatingTextPreview() {
    VioletDreamsTheme {
        VRotatingPane(
            modifier = Modifier
                .padding(10.dp)
                .size(200.dp),
        ) {
            VCircleText(
                text = "Lorem Ipsum",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VRotatingTextPreview_Toggle() {
    var rotating by remember { mutableStateOf(true) }
    VioletDreamsTheme {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .size(200.dp)
        ) {
            VButton(onClick = { rotating = !rotating }) {
                Text(if (rotating) "Stop" else "Rotate")
            }
            VRotatingPane(rotate = rotating) {
                VCircleText(
                    text = "Lorem Ipsum",
                )
            }
        }
    }
}