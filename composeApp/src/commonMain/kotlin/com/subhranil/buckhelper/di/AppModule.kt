package com.subhranil.buckhelper.di

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.subhranil.buckhelper.screens.MainViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

var appModule = module {
    viewModelOf(::MainViewModel)
//    single { GlobalResources() }
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}