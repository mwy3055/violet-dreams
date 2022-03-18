package com.practice.library.experimental

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.practice.library.theme.VioletDreamsTheme

@Composable
fun VBarrage(
    modifier: Modifier = Modifier,
    stickNumber: Int = 50,
    fireInterval: Long = 100L
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        (0 until stickNumber).forEach { index ->
            VBarrageStick(
                fireAngle = (Math.PI * 2 * index / stickNumber).toFloat(),
                fireDelay = fireInterval * index
            )
        }
    }
}

@Preview
@Composable
private fun VBarragePreview() {
    VioletDreamsTheme {
        VBarrage(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        )
    }
}