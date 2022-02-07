package com.practice.customlayout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practice.customlayout.layout.CircleText

// Number of layouts
val layoutNames = listOf(
    "Circle Text",
)

/*
    When adding a new layout
    1) Increase layoutCount
    2) Create composable below
    3) Implement new layout
 */
@Composable
fun CustomLayoutPracticeNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            LayoutHome(navController = navController)
        }
        composable("layout0") {
            val texts = listOf("Hello", "My name is", "hsk!")
            CircleText(
                modifier = Modifier.fillMaxSize(),
            ) {
                texts.forEach { text ->
                    Text(text = text)
                }
            }
        }
    }
}

@Composable
fun LayoutHome(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        layoutNames.forEachIndexed { index, layoutName ->
            Button(
                onClick = { navController.navigate("layout$index") },
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Text(text = layoutName)
            }
        }
    }
}