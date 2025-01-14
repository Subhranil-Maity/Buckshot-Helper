package com.subhranil.buckhelper

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.subhranil.buckhelper.di.initializeKoin

fun main() = application {
    initializeKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Buck Helper",
    ) {
        App()
    }
}