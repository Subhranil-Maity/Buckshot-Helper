package com.subhranil.buckhelper

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.window.layout.WindowMetricsCalculator

@Composable
actual fun isLandScapeOrPortrait(): DisplayType {
    LocalConfiguration.current
    val density = LocalDensity.current
    val metrics =
        WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(LocalContext.current)
    val size = with(density) { metrics.bounds.toComposeRect().size.toDpSize() }
    return if (size.width > size.height) DisplayType.LANDSCAPE else DisplayType.PORTRAIT
}