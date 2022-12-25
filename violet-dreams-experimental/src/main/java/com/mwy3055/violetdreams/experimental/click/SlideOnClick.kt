package com.mwy3055.violetdreams.experimental.click

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mwy3055.violetdreams.core.theme.VioletDreamsTheme

fun Modifier.slideOnClick(
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val transY by animateFloatAsState(
        targetValue = if (enabled) {
            when (buttonState) {
                ButtonState.Pressed -> 15f
                ButtonState.Idle -> 0f
            }
        } else {
            0f
        }
    )

    this
        .graphicsLayer {
            translationY = transY
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick,
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}

@Preview(showBackground = true)
@Composable
private fun SlideOnClickPreview() {
    VioletDreamsTheme {
        VTestButton(
            modifier = Modifier
                .padding(10.dp)
                .slideOnClick()
        )
    }
}