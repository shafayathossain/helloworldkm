package com.shafayat.helloworldkm.android.ui.components.bottomnavbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomNavItem(
    item: BottomNavItemModel,
    isSelected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (item.badgeCount > 0) {
            BadgedBox(
                badge = {
                    Badge {
                        Text(text = item.badgeCount.toString())
                    }
                }
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.name.extract(LocalContext.current)
                )
            }
        } else {
            Icon(
                imageVector = item.icon,
                contentDescription = item.name.extract(LocalContext.current)
            )
        }
        if (isSelected) {
            Text(text = item.name.extract(LocalContext.current))
        }
    }
}