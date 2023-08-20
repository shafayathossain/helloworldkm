package com.shafayat.helloworldkm.android.ui.theme

import androidx.compose.ui.graphics.Color

val p0 = Color(0xFF000000)
val p10 = Color(0xFF001B3F)
val p20 = Color(0xFF04428F)
val p30 = Color(0xFF055CC6)
val p40 = Color(0xFF2080F9)
val p50 = Color(0xFF58A1FB)
val p60 = Color(0xFF82BBFC)
val p70 = Color(0xFFA1CAFC)
val p80 = Color(0xFFB8D7FD)
val p90 = Color(0xFFCAE1FE)
val p95 = Color(0xFFEAF2FF)
val p99 = Color(0xFFFBFCFF)
val p100 = Color(0xFFFFFFFF)

val s0 = Color(0xFF000000)
val s10 = Color(0xFF131C2C)
val s20 = Color(0xFF283041)
val s30 = Color(0xFF3E4759)
val s40 = Color(0xFF565E71)
val s50 = Color(0xFF6F778B)
val s60 = Color(0xFF8891A5)
val s70 = Color(0xFFA3ABC0)
val s80 = Color(0xFFBEC6DC)
val s90 = Color(0xFFDAE2F9)
val s95 = Color(0xFFECF0FF)
val s99 = Color(0xFFFDFBFF)
val s100 = Color(0xFFFFFFFF)

val t0 = Color(0xFF000000)
val t10 = Color(0xFF29132E)
val t20 = Color(0xFF3F2844)
val t30 = Color(0xFF573E5B)
val t40 = Color(0xFF705574)
val t50 = Color(0xFF8A6E8E)
val t60 = Color(0xFFA587A8)
val t70 = Color(0xFFC1A1C4)
val t80 = Color(0xFFDDBCE0)
val t90 = Color(0xFFFAD8FC)
val t95 = Color(0xFFFFEBFD)
val t99 = Color(0xFFFFFBFF)
val t100 = Color(0xFFFFFFFF)

val e0 = Color(0xFF000000)
val e10 = Color(0xFF410002)
val e20 = Color(0xFF690005)
val e30 = Color(0xFF93000A)
val e40 = Color(0xFFBA1A1A)
val e50 = Color(0xFFDE3730)
val e60 = Color(0xFFFF5449)
val e70 = Color(0xFFFF897D)
val e80 = Color(0xFFFFB4AB)
val e90 = Color(0xFFFFDAD6)
val e95 = Color(0xFFFFEDEA)
val e99 = Color(0xFFFFFBFF)
val e100 = Color(0xFFFFFFFF)

val n0 = Color(0xFF000000)
val n4 = Color(0xFF0D0E11)
val n6 = Color(0xFF121316)
val n10 = Color(0xFF1A1B1F)
val n12 = Color(0xFF1F1F23)
val n17 = Color(0xFF292A2D)
val n20 = Color(0xFF46464A)
val n22 = Color(0xFF343538)
val n24 = Color(0xFF38393C)
val n30 = Color(0xFF46464A)
val n40 = Color(0xFF5E5E62)
val n50 = Color(0xFF77777A)
val n60 = Color(0xFF919094)
val n70 = Color(0xFFABABAF)
val n80 = Color(0xFFC7C6CA)
val n87 = Color(0xFFDBD9DD)
val n90 = Color(0xFFE3E2E6)
val n92 = Color(0xFFE9E7EC)
val n94 = Color(0xFFEFEDF1)
val n95 = Color(0xFFF2F0F4)
val n96 = Color(0xFFF4F3F7)
val n98 = Color(0xFFFAF9FD)
val n99 = Color(0xFFFDFBFF)
val n100 = Color(0xFFFFFFFF)

val nv0 = Color(0xFF000000)
val nv10 = Color(0xFF181C22)
val nv20 = Color(0xFF2D3038)
val nv30 = Color(0xFF44474E)
val nv40 = Color(0xFF5B5E66)
val nv50 = Color(0xFF74777F)
val nv60 = Color(0xFF8E9099)
val nv70 = Color(0xFFA9ABB4)
val nv80 = Color(0xFFC4C6D0)
val nv90 = Color(0xFFE0E2EC)
val nv95 = Color(0xFFEFF0FA)
val nv99 = Color(0xFFFDFBFF)
val nv100 = Color(0xFFFFFFFF)

class AppLightColorToken {
    companion object {
        val Primary = p40
        val OnPrimary = p100
        val PrimaryContainer = p90
        val OnPrimaryContainer = p10
        val PrimaryFixed = p90
        val PrimaryFixedDim = p80
        val OnPrimaryFixed = p10
        val OnPrimaryFixedVariant = s30

        val Secondary = s40
        val OnSecondary = s100
        val SecondaryContainer = s90
        val OnSecondaryContainer = s10
        val SecondaryFixed = s90
        val SecondaryFixedDim = s80
        val OnSecondaryFixed = s10
        val OnSecondaryFixedVariant = s30

        val Tertiary = t40
        val OnTertiary = t100
        val TertiaryContainer = t90
        val OnTertiaryContainer = t10
        val TertiaryFixed = t90
        val TertiaryFixedDim = t80
        val OnTertiaryFixed = t10
        val OnTertiaryFixedVariant = t30

        val Surface = n98
        val SurfaceDim = n87
        val SurfaceBright = n98
        val SurfaceContainerLowest = n100
        val SurfaceContainerLow = n96
        val SurfaceContainer = n94
        val SurfaceContainerHigh = n92
        val SurfaceContainerHighest = n90
        val OnSurface = n10
        val OnSurfaceVariant = nv30

        val Outline = nv50
        val OutlineVariant = nv80

        val Error = e40
        val OnError = e100
        val ErrorContainer = e90
        val OnErrorContainer = e10

        val InverseSurface = n20
        val InverseOnSurface = n95
        val InversePrimary = p80
        val Scrim = n0
        val Shadow = n0
    }
}

class AppDarkColorToken {
    companion object {
        val Primary = p80
        val OnPrimary = p20
        val PrimaryContainer = p30
        val OnPrimaryContainer = p90
        val PrimaryFixed = p90
        val PrimaryFixedDim = p80
        val OnPrimaryFixed = p10
        val OnPrimaryFixedVariant = s30

        val Secondary = s80
        val OnSecondary = s20
        val SecondaryContainer = s30
        val OnSecondaryContainer = s90
        val SecondaryFixed = s90
        val SecondaryFixedDim = s80
        val OnSecondaryFixed = s10
        val OnSecondaryFixedVariant = s80

        val Tertiary = t80
        val OnTertiary = t20
        val TertiaryContainer = t30
        val OnTertiaryContainer = t90
        val TertiaryFixed = t90
        val TertiaryFixedDim = t80
        val OnTertiaryFixed = t10
        val OnTertiaryFixedVariant = t30

        val Surface = n6
        val SurfaceDim = n6
        val SurfaceBright = n24
        val SurfaceContainerLowest = n4
        val SurfaceContainerLow = n10
        val SurfaceContainer = n12
        val SurfaceContainerHigh = n17
        val SurfaceContainerHighest = n22
        val OnSurface = n90
        val OnSurfaceVariant = nv80

        val Outline = nv60
        val OutlineVariant = nv30

        val Error = e80
        val OnError = e20
        val ErrorContainer = e30
        val OnErrorContainer = e90

        val InverseSurface = n90
        val InverseOnSurface = n20
        val InversePrimary = p40
        val Scrim = n0
        val Shadow = n0
    }
}



