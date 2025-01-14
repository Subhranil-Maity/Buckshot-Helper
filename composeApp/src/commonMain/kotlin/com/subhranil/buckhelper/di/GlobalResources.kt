package com.subhranil.buckhelper.di

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.compose.getKoin
import org.koin.compose.koinInject

object GlobalResources {
    private lateinit var navHostController: NavHostController

    fun setNavController(navController: NavHostController) {
        navHostController = navController
    }
    fun getNavController() = navHostController

}

@Composable
fun setupGlobalResources() {
    val navHostController = rememberNavController()
    GlobalResources.setNavController(navHostController)
}