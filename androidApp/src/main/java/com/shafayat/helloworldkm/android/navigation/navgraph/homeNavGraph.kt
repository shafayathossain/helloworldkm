package com.shafayat.helloworldkm.android.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.shafayat.helloworldkm.android.features.home.HomeScreen
import com.shafayat.helloworldkm.android.navigation.Route

fun NavGraphBuilder.homeNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Route.homeScreen,
        route = Route.homeRoute
    ) {
        composable(Route.homeScreen) {
            HomeScreen.BaseComposable(navHostController)
        }
    }
}