package com.practice.customlayout.layout

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.practice.customlayout.ui.theme.CustomLayoutPracticeTheme
import kotlin.math.max

@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    rows: Int = 5,
    horizontalMargin: Dp = 10.dp,
    verticalMargin: Dp = 5.dp,
    content: @Composable () -> Unit = {}
) {
    Layout(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .verticalScroll(rememberScrollState()),
        content = content
    ) { measurables, constraints ->
        // Measure Children
        val horizontalMarginPx = horizontalMargin.roundToPx()
        val verticalMarginPx = verticalMargin.roundToPx()
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        // Decide own size
        var width = 0
        var height = 0

        var rowMaxWidth = 0
        var rowMaxHeight = 0
        var rowCount = 0
        placeables.forEach { placeable ->
            rowMaxWidth = max(rowMaxWidth, placeable.width)
            rowMaxHeight += placeable.height + verticalMarginPx
            rowCount++
            if (rowCount % rows == 0) {
//              remove last vertical margin
                rowMaxHeight -= verticalMarginPx

                width += rowMaxWidth + horizontalMarginPx
                height = max(height, rowMaxHeight)
                rowMaxWidth = 0
                rowMaxHeight = 0
            }
        }
        if (rowMaxWidth == 0 && rowMaxHeight == 0) {
//          remove last horizontal margin
            width -= horizontalMarginPx
        } else {
            width += rowMaxWidth
            height = max(height, rowMaxHeight)
        }

        // Place children
        layout(width, height) {
            var x = 0
            var y = 0
            var maxWidth = 0
            var count = 0
            placeables.forEach { placeable ->
                placeable.place(x, y)
                maxWidth = max(maxWidth, placeable.width)
                y += placeable.height + verticalMarginPx

                count++
                if (count % rows == 0) {
                    x += maxWidth + horizontalMarginPx
                    y = 0
                    maxWidth = 0
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun StaggeredGridPreview() {
    val texts =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
            .split(" ")
    CustomLayoutPracticeTheme {
        StaggeredGrid {
            texts.forEach { text -> Text(text = text) }
        }
    }
}