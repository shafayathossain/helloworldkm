package com.shafayat.helloworldkm.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration

private val DarkColorPalette = darkColorScheme(
    primary = AppDarkColorToken.Primary,
    onPrimary = AppDarkColorToken.OnPrimary,
    primaryContainer = AppDarkColorToken.PrimaryContainer,
    onPrimaryContainer = AppDarkColorToken.OnPrimaryContainer,
    inversePrimary = AppDarkColorToken.InversePrimary,
    secondary = AppDarkColorToken.Secondary,
    onSecondary = AppDarkColorToken.OnSecondary,
    secondaryContainer = AppDarkColorToken.SecondaryContainer,
    onSecondaryContainer = AppDarkColorToken.OnSecondaryContainer,
    tertiary = AppDarkColorToken.Tertiary,
    onTertiary = AppDarkColorToken.OnTertiary,
    tertiaryContainer = AppDarkColorToken.TertiaryContainer,
    onTertiaryContainer = AppDarkColorToken.OnTertiaryContainer,
    background = AppDarkColorToken.Surface,
    onBackground = AppDarkColorToken.OnSurface,
    surface = AppDarkColorToken.Surface,
    onSurface = AppDarkColorToken.OnSurface,
    surfaceVariant = AppDarkColorToken.SurfaceContainer,
    onSurfaceVariant = AppDarkColorToken.OnSurfaceVariant,
    surfaceTint = AppDarkColorToken.Primary,
    inverseSurface = AppDarkColorToken.InverseSurface,
    inverseOnSurface = AppDarkColorToken.InverseOnSurface,
    error = AppDarkColorToken.Error,
    onError = AppDarkColorToken.OnError,
    errorContainer = AppDarkColorToken.ErrorContainer,
    onErrorContainer = AppDarkColorToken.OnErrorContainer,
    outline = AppDarkColorToken.Outline,
    outlineVariant = AppDarkColorToken.OutlineVariant,
    scrim = AppDarkColorToken.Scrim,
)

private val LightColorPalette = lightColorScheme(
    primary = AppLightColorToken.Primary,
    onPrimary = AppLightColorToken.OnPrimary,
    primaryContainer = AppLightColorToken.PrimaryContainer,
    onPrimaryContainer = AppLightColorToken.OnPrimaryContainer,
    inversePrimary = AppLightColorToken.InversePrimary,
    secondary = AppLightColorToken.Secondary,
    onSecondary = AppLightColorToken.OnSecondary,
    secondaryContainer = AppLightColorToken.SecondaryContainer,
    onSecondaryContainer = AppLightColorToken.OnSecondaryContainer,
    tertiary = AppLightColorToken.Tertiary,
    onTertiary = AppLightColorToken.OnTertiary,
    tertiaryContainer = AppLightColorToken.TertiaryContainer,
    onTertiaryContainer = AppLightColorToken.OnTertiaryContainer,
    background = AppLightColorToken.Surface,
    onBackground = AppLightColorToken.OnSurface,
    surface = AppLightColorToken.Surface,
    onSurface = AppLightColorToken.OnSurface,
    surfaceVariant = AppLightColorToken.SurfaceContainer,
    onSurfaceVariant = AppLightColorToken.OnSurfaceVariant,
    surfaceTint = AppLightColorToken.Primary,
    inverseSurface = AppLightColorToken.InverseSurface,
    inverseOnSurface = AppLightColorToken.InverseOnSurface,
    error = AppLightColorToken.Error,
    onError = AppLightColorToken.OnError,
    errorContainer = AppLightColorToken.ErrorContainer,
    onErrorContainer = AppLightColorToken.OnErrorContainer,
    outline = AppLightColorToken.Outline,
    outlineVariant = AppLightColorToken.OutlineVariant,
    scrim = AppLightColorToken.Scrim,
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    val elevations = if (darkTheme) darkElevations else lightElevations

    val densityDpi = LocalConfiguration.current.densityDpi
    val spacing = when {
        (densityDpi < 320) -> compactSpacing
        (densityDpi < 480) -> normalSpacing
        else -> expandedSpacing
    }

    CompositionLocalProvider(
        //changing the LocalElevations based on DarkMode
        LocalElevations.provides(elevations),
        //changing the LocalSpacing based on Screen size
        LocalSpacing.provides(spacing)
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = Typography,
            shapes = roundedCornerShapes,
            content = content
        )
    }
}