package com.news.noticiasapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.news.noticiasapp.viewmodels.NewsViewModel
import com.news.noticiasapp.views.AddNewsScreen
import com.news.noticiasapp.views.EditNewsScreen
import com.news.noticiasapp.views.NewsDetailScreen
import com.news.noticiasapp.views.NewsListScreen

sealed class Screen(val route: String) {
    object NewsList : Screen("news_list")
    object NewsDetail : Screen("news_detail/{newsId}") {
        fun createRoute(newsId: Int) = "news_detail/$newsId"
    }
    object AddNews : Screen("add_news")
    object EditNews : Screen("edit_news/{newsId}") {
        fun createRoute(newsId: Int) = "edit_news/$newsId"
    }
}

@Composable
fun NewsNavGraph(
    navController: NavHostController,
    newsViewModel: NewsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NewsList.route
    ) {
        composable(Screen.NewsList.route) {
            NewsListScreen(
                onNewsClick = { newsId ->
                    navController.navigate(Screen.NewsDetail.createRoute(newsId))
                },
                onAddClick = {
                    navController.navigate(Screen.AddNews.route)
                }
            )
        }

        composable(
            route = Screen.NewsDetail.route,
            arguments = listOf(navArgument("newsId") { type = NavType.IntType })
        ) { backStackEntry ->
            val newsId = backStackEntry.arguments?.getInt("newsId") ?: return@composable
            NewsDetailScreen(
                newsId = newsId,
                onEditClick = {
                    navController.navigate(Screen.EditNews.createRoute(newsId))
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onDeleteSuccess = {
                    navController.navigate(Screen.NewsList.route)
                }
            )
        }

        composable(Screen.AddNews.route) {
            AddNewsScreen(
                onNewsAdded = {
                    navController.navigate(Screen.NewsList.route)
                }
            )
        }

        composable(
            route = Screen.EditNews.route,
            arguments = listOf(navArgument("newsId") { type = NavType.IntType })
        ) { backStackEntry ->
            val newsId = backStackEntry.arguments?.getInt("newsId") ?: return@composable
            EditNewsScreen(
                newsId = newsId,
                onNewsUpdated = {
                    navController.navigate(Screen.NewsList.route)
                }
            )
        }
    }
}
