package com.example.wealthai.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wealthai.presentation.auth.login.LoginScreen
import com.example.wealthai.presentation.auth.signup.SignUpScreen
import com.example.wealthai.presentation.company_info.CompanyInfoScreen
import com.example.wealthai.presentation.company_listing.CompanyListingScreen
import com.example.wealthai.presentation.portfolio.PortfolioScreen
import com.example.wealthai.presentation.screens.Screen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CompanyListingScreen.route,
    ) {
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController = navController)
        }

        composable(
            route = Screen.SignUpScreen.route
        ) {
            SignUpScreen(navController = navController) {
                navController.popBackStack()
            }
        }

        composable(
            route = Screen.CompanyListingScreen.route
        ) {
            CompanyListingScreen(navController = navController)
        }

        composable(
            route = Screen.PortfolioScreen.route,
        ) {
            PortfolioScreen(navController = navController)
        }


//        composable(
//            route = Screen.CompanyInfoScreen.route,
//        ) {
//            CompanyInfoScreen("TSLA")
//        }

        composable(
            route = Screen.CompanyInfoScreen.route + "/{CompanySymbol}",
            arguments = listOf(
                navArgument("CompanySymbol") {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.getString("CompanySymbol")?.let { it1 -> CompanyInfoScreen(symbol = it1) }
        }

    }

}