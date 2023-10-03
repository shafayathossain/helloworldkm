package com.shafayat.helloworldkm.android.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.shafayat.helloworldkm.android.features.main.MainScreen
import com.shafayat.helloworldkm.android.navigation.Route

fun NavGraphBuilder.MainNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Route.mainScreen,
        route = Route.mainRoute
    ) {
        composable(Route.mainScreen) {
            MainScreen.BaseComposable(navHostController, rememberNavController())
        }
    }
}