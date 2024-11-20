package com.mtc.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
class FestimateColors(
    mainCoral: Color,
    subCoral: Color,
    alertRed: Color,
    alertBlue: Color,
    kakaoYellow: Color,
    white: Color,
    gray01: Color,
    gray02: Color,
    gray03: Color,
    gray04: Color,
    gray05: Color,
    gray06: Color,
) {
    var mainCoral by mutableStateOf(mainCoral)
        private set
    var subCoral by mutableStateOf(subCoral)
        private set
    var alertRed by mutableStateOf(alertRed)
        private set
    var alertBlue by mutableStateOf(alertBlue)
        private set
    var kakaoYellow by mutableStateOf(kakaoYellow)
        private set
    var white by mutableStateOf(white)
        private set
    var gray01 by mutableStateOf(gray01)
        private set
    var gray02 by mutableStateOf(gray02)
        private set
    var gray03 by mutableStateOf(gray03)
        private set
    var gray04 by mutableStateOf(gray04)
        private set
    var gray05 by mutableStateOf(gray05)
        private set
    var gray06 by mutableStateOf(gray06)
        private set
}

fun FestimateColor(
    mainCoral: Color = MainCoral,
    subCoral: Color = SubCoral,
    alertRed: Color = AlertRed,
    alertBlue: Color = AlertBlue,
    kakaoYellow: Color = KakaoYellow,
    white: Color = White,
    gray01: Color = Gray01,
    gray02: Color = Gray02,
    gray03: Color = Gray03,
    gray04: Color = Gray04,
    gray05: Color = Gray05,
    gray06: Color = Gray06,
) = FestimateColors(
    mainCoral,
    subCoral,
    alertRed,
    alertBlue,
    kakaoYellow,
    white,
    gray01,
    gray02,
    gray03,
    gray04,
    gray05,
    gray06,
)

private val LocalFestimateColors =
    staticCompositionLocalOf<FestimateColors> { error("provide none color") }

private val LocalFestimateTypography =
    staticCompositionLocalOf<FestimateTypography> { error("provide none typography") }

object FestimateTheme {
    val colors: FestimateColors
        @Composable
        @ReadOnlyComposable
        get() = LocalFestimateColors.current

    val typography: FestimateTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalFestimateTypography.current
}

@Composable
fun provideColorsAndTypography(
    colors: FestimateColors,
    typography: FestimateTypography,
    content: @Composable () -> Unit,
) {
    val provideColors = remember { colors }
    val provideTypography = remember { typography }

    CompositionLocalProvider(
        LocalFestimateColors provides colors,
        LocalFestimateTypography provides typography,
        content = content,
    )
}

@Composable
fun FestimateTheme(content: @Composable () -> Unit) {
    val colors = FestimateColor()
    val typography = FestimateTypography()
    provideColorsAndTypography(colors, typography) {
        MaterialTheme(content = content)
    }
}
