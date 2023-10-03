package com.shafayat.helloworldkm.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.shafayat.helloworldkm.android.features.contents.ContentsScreen
import com.shafayat.helloworldkm.android.features.profile.ProfileScreen
import com.shafayat.helloworldkm.android.features.home.HomeScreen

@Composable
fun HomeNavHost(
    navHostController: NavHostController,
    bottomNavHostController: NavHostController,
) {
    NavHost(
        navController = bottomNavHostController,
        startDestination = Route.homeScreen
    ) {
        composable(Route.homeScreen) {
            HomeScreen.BaseComposable(navHostController, bottomNavHostController)
        }
        composable(Route.contentsScreen) {
            ContentsScreen.BaseComposable(navHostController, bottomNavHostController)
        }
        composable(Route.profileScreen) {
            ProfileScreen.BaseComposable(navHostController, bottomNavHostController)
        }
    }
}
