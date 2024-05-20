package com.example.primodemoappliicaton.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.primodemoappliicaton.ui.HomeScreen
import com.example.primodemoappliicaton.ui.article.ArticleScreen

enum class Screen {
    HOME,
    ARTICLE,
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Article : NavigationItem(Screen.ARTICLE.name)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController)
        }

        composable(
            route = "${NavigationItem.Article.route}/{feed_id}",
            arguments = listOf(navArgument("feed_id") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            ArticleScreen(
                navController,
                backStackEntry.arguments?.getInt("feed_id")
            )
        }
    }
}