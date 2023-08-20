package com.shafayat.helloworldkm.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.shafayat.helloworldkm.android.navigation.navgraph.homeNavGraph

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    startDestination: String = Route.homeRoute,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        route = Route.rootRoute
    ) {
        homeNavGraph(navHostController)
    }
}