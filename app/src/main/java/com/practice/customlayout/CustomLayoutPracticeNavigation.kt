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
import com.practice.library.VStaggeredGrid

// Number of layouts
val layoutNames = listOf(
    "Staggered Grid",
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
            val texts =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
                    .split(" ")
            VStaggeredGrid(
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