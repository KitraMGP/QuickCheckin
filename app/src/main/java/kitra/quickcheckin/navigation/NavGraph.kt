package kitra.quickcheckin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kitra.quickcheckin.screens.ComposableClassManagementScreen
import kitra.quickcheckin.screens.ComposableHomeScreen
import kitra.quickcheckin.screens.ComposablePrepareScreen
import kitra.quickcheckin.screens.ComposableStudentManagementScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    // TODO 动画存在困难，先不做了
    NavHost(
        navController = navController,
        startDestination = "home",
    ) {
        composable("home") { ComposableHomeScreen(navController) }
        composable("prepare") { ComposablePrepareScreen(navController) }
        composable("class_management") { ComposableClassManagementScreen(navController) }
        composable("student_management") { ComposableStudentManagementScreen(navController) }
    }
}
