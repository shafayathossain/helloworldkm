package com.shafayat.helloworldkm.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.shafayat.helloworldkm.android.navigation.navgraph.MainNavGraph

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    startDestination: String = Route.mainRoute,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        route = Route.rootRoute
    ) {
        MainNavGraph(navHostController)
    }
}