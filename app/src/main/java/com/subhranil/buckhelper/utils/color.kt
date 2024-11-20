package com.subhranil.buckhelper.utils

import androidx.compose.ui.graphics.Color

/**
 * Tints a primary color using a secondary color by a specified percentage.
 *
 * @param primary The primary color to be tinted.
 * @param secondary The secondary color to blend with.
 * @param tintPercentage The percentage of the tint (0.0 to 1.0).
 * @return A new color representing the blend.
 */
fun tintColor(primary: Color, secondary: Color, tintPercentage: Float): Color {
    val clampedPercentage = tintPercentage.coerceIn(0f, 1f)

    // Interpolate the RGBA components
    val red = (primary.red * (1 - clampedPercentage)) + (secondary.red * clampedPercentage)
    val green = (primary.green * (1 - clampedPercentage)) + (secondary.green * clampedPercentage)
    val blue = (primary.blue * (1 - clampedPercentage)) + (secondary.blue * clampedPercentage)
    val alpha = (primary.alpha * (1 - clampedPercentage)) + (secondary.alpha * clampedPercentage)

    return Color(red, green, blue, alpha)
}