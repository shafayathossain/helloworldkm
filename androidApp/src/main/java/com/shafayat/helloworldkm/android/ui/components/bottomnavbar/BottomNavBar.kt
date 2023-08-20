package com.shafayat.helloworldkm.android.ui.components.bottomnavbar

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(
    navHostController: NavHostController,
    items: List<BottomNavItemModel>,
    modifier: Modifier,
    onItemClick: (BottomNavItemModel) -> Unit
) {
    val backStackEntry = navHostController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp,
        actions = {
            items.forEach { item ->
                val isSelected = (currentRoute == item.route)
                NavigationBarItem(
                    selected = isSelected,
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name.extract(LocalContext.current)
                        )
                    },
                    onClick = { onItemClick(item) }
                )
            }
        },
    )
}