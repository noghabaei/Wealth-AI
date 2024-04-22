package com.example.wealthai.presentation.screens

import com.example.wealthai.presentation.screens.Constants.COMPANY_INFO_SCREEN
import com.example.wealthai.presentation.screens.Constants.COMPANY_LISTING_SCREEN
import com.example.wealthai.presentation.screens.Constants.LOGIN_SCREEN
import com.example.wealthai.presentation.screens.Constants.PORTFOLIO_SCREEN
import com.example.wealthai.presentation.screens.Constants.SIGN_UP_SCREEN

sealed class Screen(val route: String) {
    object LoginScreen : Screen(LOGIN_SCREEN)

    object SignUpScreen : Screen(SIGN_UP_SCREEN)

    object CompanyListingScreen : Screen(COMPANY_LISTING_SCREEN)

    object CompanyInfoScreen : Screen(COMPANY_INFO_SCREEN)

    object PortfolioScreen : Screen(PORTFOLIO_SCREEN)


    fun withArg(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}