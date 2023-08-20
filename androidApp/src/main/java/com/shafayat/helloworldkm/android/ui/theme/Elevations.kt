package com.shafayat.helloworldkm.android.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevations(
    val default: Dp = 0.dp,
    val extraSmall: Dp,
    val small: Dp,
    val large: Dp,
    val extraLarge: Dp,
)


//This instance can be accessed by all composable in the app
val LocalElevations = compositionLocalOf { lightElevations }

val lightElevations = Elevations(
    extraSmall = 1.dp,
    small = 4.dp,
    large = 8.dp,
    extraLarge = 12.dp
)
val darkElevations = Elevations(
    extraSmall = 2.dp,
    small = 6.dp,
    large = 10.dp,
    extraLarge = 16.dp
)