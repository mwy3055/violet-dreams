package com.mwy3055.violetdreams.experimental.barrage

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mwy3055.violetdreams.core.theme.VioletDreamsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun VBarrageStick(
    fireAngle: Float,
    modifier: Modifier = Modifier,
    fireDelay: Long = 0,
) {
    // Animatable provides non-composable functions to animate a value
    val offsetX = remember { Animatable(initialValue = 0f) }
    val offsetY = remember { Animatable(initialValue = 0f) }
    val stickAngle = remember { Animatable(initialValue = fireAngle) }

    LaunchedEffect(key1 = fireAngle) {
        delay(fireDelay)
        // Fire
        val fireRadius = 200
        val fireDuration = 1000
        val fireDestX = fireRadius * cos(stickAngle.value)
        val fireDestY = fireRadius * sin(stickAngle.value)
        launch {
            launch {
                offsetX.animateTo(
                    targetValue = fireDestX,
                    animationSpec = tween(
                        durationMillis = fireDuration,
                        easing = LinearEasing
                    )
                )
            }
            launch {
                offsetY.animateTo(
                    targetValue = fireDestY,
                    animationSpec = tween(
                        durationMillis = fireDuration,
                        easing = LinearEasing
                    )
                )
            }
        }.join()
//        delay(500)
        // Rotate
        val rotateDuration = 500
        stickAngle.animateTo(
            targetValue = fireAngle + (Math.PI * 5 / 12).toFloat(),
            animationSpec = tween(
                durationMillis = rotateDuration,
                easing = FastOutLinearInEasing
            )
        )
//        delay(500)
        // Move on line
        val lineLength = 1000
        val lineDuration = 3000
        val moveDiffX = lineLength * cos(stickAngle.value)
        val moveDiffY = lineLength * sin(stickAngle.value)
        launch {
            launch {
                offsetX.animateTo(
                    targetValue = fireDestX + moveDiffX,
                    animationSpec = tween(
                        durationMillis = lineDuration,
                        easing = LinearEasing
                    )
                )
            }
            launch {
                offsetY.animateTo(
                    targetValue = fireDestY + moveDiffY,
                    animationSpec = tween(
                        durationMillis = lineDuration,
                        easing = LinearEasing
                    )
                )
            }
        }.join()
    }

    val stickWidth = 10.dp
    VStick(
        modifier = modifier
            .offset(x = offsetX.value.dp, y = offsetY.value.dp)
            .size(width = stickWidth, height = stickWidth * 5)
            .rotate(stickAngle.value.toModifierDegree())
    )
}

fun Float.toModifierDegree(): Float = Math.toDegrees(this + Math.PI / 2).toFloat()

@Preview(showBackground = true)
@Composable
fun VBarrageStickPreview() {
    VioletDreamsTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            VBarrageStick(
                fireAngle = (Math.PI * 1 / 4).toFloat(),
            )
        }
    }
}