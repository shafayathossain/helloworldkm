package com.shafayat.helloworldkm.android.ui.components.bottomnavbar

import androidx.compose.ui.graphics.vector.ImageVector
import com.shafayat.helloworldkm.android.ui.components.text.UiText

data class BottomNavItemModel(
    val name: UiText,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)