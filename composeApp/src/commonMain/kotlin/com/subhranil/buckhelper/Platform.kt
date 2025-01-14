package com.subhranil.buckhelper

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform