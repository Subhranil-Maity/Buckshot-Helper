package com.subhranil.buckhelper

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.toSize

enum class DisplayType {
    LANDSCAPE,
    PORTRAIT
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
expect fun isLandScapeOrPortrait(): DisplayType
