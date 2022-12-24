package com.mwy3055.violetdreams.ui.element

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mwy3055.violetdreams.core.shape.VBezierShape
import com.mwy3055.violetdreams.core.theme.VioletDreamsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    border: BorderStroke = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primaryVariant),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    val contentColor by colors.contentColor(enabled)
    Surface(
        onClick = onClick,
        modifier = modifier.graphicsLayer {
            shape = VBezierShape()
            clip = true
        },
        enabled = enabled,
        shape = VBezierShape(),
        color = colors.backgroundColor(enabled = enabled).value,
        contentColor = contentColor.copy(alpha = 1f),
        border = border,
//        elevation = elevation?.elevation(enabled, interactionSource)?.value ?: 0.dp,
        interactionSource = interactionSource
    ) {
        CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
            ProvideTextStyle(value = MaterialTheme.typography.button) {
                Row(
                    modifier = Modifier
                        .defaultMinSize(
                            minWidth = ButtonDefaults.MinWidth,
                            minHeight = ButtonDefaults.MinHeight
                        )
                        .padding(contentPadding),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = content
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VButtonPreview() {
    VioletDreamsTheme {
        VButton(
            onClick = {},
            modifier = Modifier.padding(10.dp)
        ) {
            Text("I want to go home")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VButtonPreviewShortText() {
    VioletDreamsTheme {
        VButton(
            onClick = {},
            modifier = Modifier.padding(10.dp),
        ) {
            Text("확인")
        }
    }
}