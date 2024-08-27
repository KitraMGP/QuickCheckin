package kitra.quickcheckin.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kitra.quickcheckin.screens.PrepareScreen
import kitra.quickcheckin.screens.ComposableHomeScreen

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    // TODO 动画存在困难，先不做了
    NavHost(
        navController = navController,
        startDestination = "home",
        /*enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }*/
    ) {
        composable("home",
            /*enterTransition = {
                fadeIn(animationSpec = tween(durationMillis = 1000))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(durationMillis = 1000))
            }*/) { ComposableHomeScreen(navController) }
        composable("prepare",
            /*popEnterTransition = {
                fadeIn(animationSpec = tween(1000)) +
                slideIntoContainer(animationSpec = tween(1000), towards = AnimatedContentTransitionScope.SlideDirection.Start)
            },
            popExitTransition = {
                fadeOut(animationSpec = tween(1000)) +
                slideOutOfContainer(animationSpec = tween(1000), towards = AnimatedContentTransitionScope.SlideDirection.End)
            }*/) { PrepareScreen(navController) }
    }
}
