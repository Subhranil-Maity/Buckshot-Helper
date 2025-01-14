package com.subhranil.buckhelper

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun isLandScapeOrPortrait(): DisplayType {
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current.containerSize
    val size = with(density) { windowInfo.toSize().toDpSize() }
    return if (size.width > size.height) DisplayType.LANDSCAPE else DisplayType.PORTRAIT
}