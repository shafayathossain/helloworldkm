package com.shafayat.helloworldkm.android.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.runtime.Composable
import com.shafayat.helloworldkm.android.R
import com.shafayat.helloworldkm.android.navigation.Route
import com.shafayat.helloworldkm.android.ui.components.bottomnavbar.BottomNavItemModel
import com.shafayat.helloworldkm.android.ui.components.text.UiText

@Composable
fun getBottomNavItems(): List<BottomNavItemModel> =
    listOf(
        BottomNavItemModel(
            name = UiText.DynamicText(R.string.home),
            route = Route.homeScreen,
            icon = Icons.Outlined.Home,
        ),
        BottomNavItemModel(
            name = UiText.DynamicText(R.string.contents),
            route = Route.contentsScreen,
            icon = Icons.Outlined.ListAlt,
        ),
        BottomNavItemModel(
            name = UiText.DynamicText(R.string.profile),
            route = Route.profileScreen,
            icon = Icons.Outlined.AccountCircle,
        ),
    )