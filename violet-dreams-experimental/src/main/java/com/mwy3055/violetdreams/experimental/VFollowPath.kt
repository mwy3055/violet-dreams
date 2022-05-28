package com.mwy3055.violetdreams.experimental

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mwy3055.violetdreams.core.theme.VioletDreamsTheme
import kotlin.math.min

/**
 * TODO: Find the way to animate the path (from start to end)
 */

/**
 * @param path List of relative positions to be drawn (between 0 to 1, both inclusive)
 */
@Composable
fun VFollowPath(
    path: List<Offset>,
    modifier: Modifier = Modifier
) {
    val color = MaterialTheme.colors.primary
    Canvas(
        modifier = modifier,
        onDraw = {
            val scaledPath = path.map { Offset(x = it.x * size.width, y = it.y * size.height) }
            val minSize = min(size.width, size.height)

            if (path.size == 1) {
                drawCircle(
                    color = color,
                    radius = min(minSize / 50, 10f),
                    center = scaledPath[0],
                )
            } else {
                drawPoints(
                    points = scaledPath,
                    pointMode = PointMode.Polygon,
                    color = color,
                    strokeWidth = minSize / 50,
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun VFollowPathPreview() {
    val path = (0..8).map {
        Offset((it % 2).toFloat(), 0.125f * it)
    }
    VioletDreamsTheme {
        VFollowPath(
            path = path,
            modifier = Modifier
                .padding(20.dp)
                .size(400.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VFollowPathSinglePointPreview() {
    val point = Offset(0.25f, 0.25f)
    VioletDreamsTheme {
        VFollowPath(
            path = listOf(point),
            modifier = Modifier
                .padding(20.dp)
                .size(400.dp)
        )
    }
}