package com.mwy3055.violetdreams.experimental.click

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
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

fun Modifier.shakeOnClick(
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val transX by animateFloatAsState(
        targetValue = if (enabled) {
            when (buttonState) {
                ButtonState.Pressed -> 25f
                ButtonState.Idle -> 0f
            }
        } else {
            0f
        },
        animationSpec = repeatable(
            iterations = 2,
            animation = tween(durationMillis = 40, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        )
    )

    this
        .graphicsLayer {
            translationX = transX
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
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
private fun ShakeOnClickPreview() {
    VioletDreamsTheme {
        VTestButton(
            modifier = Modifier
                .padding(10.dp)
                .shakeOnClick(),
            rippleTheme = EmptyRippleTheme,
        )
    }
}