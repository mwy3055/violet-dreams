package com.practice.library.example

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * Compose library
 */
@Composable
fun SomeView(
    someState: SomeState = remember { SomeState() }
) {
    // Some layouts
}