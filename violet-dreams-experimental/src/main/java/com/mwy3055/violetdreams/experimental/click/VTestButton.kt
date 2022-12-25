package com.mwy3055.violetdreams.experimental.click

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mwy3055.violetdreams.core.theme.VioletDreamsTheme
import com.mwy3055.violetdreams.ui.element.VButton

@Composable
fun VTestButton(
    modifier: Modifier = Modifier,
    rippleTheme: RippleTheme = LocalRippleTheme.current,
    onClick: () -> Unit = {},
) {
    VButton(
        onClick = onClick,
        modifier = modifier.size(width = 161.dp, height = 40.dp),
        rippleTheme = rippleTheme,
    ) {
        Text(text = "Hello Button!")
    }
}

@Preview(showBackground = true)
@Composable
private fun VTestButtonPreview() {
    VioletDreamsTheme {
        VTestButton(modifier = Modifier.padding(10.dp))
    }
}