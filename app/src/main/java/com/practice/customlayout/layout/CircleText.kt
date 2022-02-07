package com.practice.customlayout.layout

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.practice.customlayout.ui.theme.CustomLayoutPracticeTheme
import kotlin.math.min

@Composable
fun CircleText(
    modifier: Modifier = Modifier,
    gap: Dp = 10.dp,
    content: @Composable () -> Unit = {}
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
//        Measure children
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        val width = constraints.maxWidth
        val height = constraints.maxHeight
        val radius = min(width, height) / 2
        val actualPerimeter = radius * radius * Math.PI

        val perimeter = placeables.sumOf { it.width }.dp + gap * (measurables.size - 1)
        val resizeRatio = perimeter.toPx() / actualPerimeter

        layout(width, height) {
//            어떻게 휘게 만들 것인가?
            placeables.forEach { placeable ->


            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun CircleTextPreview() {
    val texts = listOf(
        "Lorem ipsum",
        "dolor sit amet",
        "consectetur adipiscing elit",
        "sed do eiusmod",
        "tempor incididunt"
    )
    CustomLayoutPracticeTheme {
        CircleText {
            texts.forEach { text -> Text(text = text) }
        }
    }
}