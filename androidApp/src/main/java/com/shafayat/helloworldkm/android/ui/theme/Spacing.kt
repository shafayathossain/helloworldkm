package com.shafayat.helloworldkm.android.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp,
    val small: Dp,
    val large: Dp,
    val extraLarge: Dp,
)

//This instance can be accessed by all composable in the app
val LocalSpacing = compositionLocalOf { normalSpacing }

val compactSpacing = Spacing(
    extraSmall = 4.dp,
    small = 6.dp,
    large = 16.dp,
    extraLarge = 32.dp
)
val normalSpacing = Spacing(
    extraSmall = 8.dp,
    small = 16.dp,
    large = 32.dp,
    extraLarge = 64.dp
)
val expandedSpacing = Spacing(
    extraSmall = 16.dp,
    small = 32.dp,
    large = 64.dp,
    extraLarge = 128.dp
)