package com.practice.customlayout.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practice.library.experimental.VPolygon
import com.practice.library.theme.VioletDreamsTheme
import kotlin.math.roundToInt

@Composable
fun VPolygonScreen(
    sides: Int,
    onSidesChange: (Int) -> Unit,
    lineWidth: Float,
    onLineWidthChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    progress: Float = 1f,
    onProgressChange: (Float) -> Unit = {},
    phase: Float = 0f,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        VPolygon(
            sides = sides,
            modifier = Modifier.padding(10.dp),
            lineWidth = lineWidth.dp,
            interval = floatArrayOf(progress, 1 - progress),
            phase = phase,
        )
        Column {
            SidesPanel(sides = sides, onSidesChange = onSidesChange)
            ProgressPanel(progress = progress, onProgressChange = onProgressChange)
            LineWidthPanel(lineWidth = lineWidth, onLineWidthChange = onLineWidthChange)
        }
    }
}

@Composable
private fun SidesPanel(sides: Int, onSidesChange: (Int) -> Unit) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = "Sides",
            style = MaterialTheme.typography.body1
        )
        Slider(
            value = sides.toFloat(),
            onValueChange = {
                onSidesChange(it.roundToInt())
            },
            valueRange = 3f..20f,
        )
    }
}

@Composable
private fun ProgressPanel(
    progress: Float,
    onProgressChange: (Float) -> Unit
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = "Progress",
            style = MaterialTheme.typography.body1
        )
        Slider(
            value = progress,
            onValueChange = onProgressChange,
        )
    }
}

@Composable
private fun LineWidthPanel(
    lineWidth: Float,
    onLineWidthChange: (Float) -> Unit
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = "Line width",
            style = MaterialTheme.typography.body1
        )
        Slider(
            value = lineWidth,
            onValueChange = onLineWidthChange,
            valueRange = 10f..50f,
        )
    }
}

@Preview
@Composable
private fun VPolygonScreenPreview() {
    var sides by remember { mutableStateOf(3) }
    var lineWidth by remember { mutableStateOf(10f) }

    VioletDreamsTheme {
        VPolygonScreen(
            sides = sides,
            onSidesChange = {
                sides = it
            },
            lineWidth = lineWidth,
            onLineWidthChange = {
                lineWidth = it
            }
        )
    }
}