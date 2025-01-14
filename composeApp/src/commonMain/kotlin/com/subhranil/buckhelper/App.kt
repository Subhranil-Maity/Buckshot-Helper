package com.subhranil.buckhelper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.subhranil.buckhelper.di.GlobalResources
import com.subhranil.buckhelper.di.setupGlobalResources
import com.subhranil.buckhelper.screens.MyNavHostController
import com.subhranil.buckhelper.ui.BuckHelperTheme
import com.subhranil.buckhelper.ui.components.AddBurnerActionButton
import org.koin.compose.KoinContext
import org.koin.compose.koinInject



@Composable
fun App() {
    BuckHelperTheme {
        KoinContext {
            setupGlobalResources()
            val navController = GlobalResources.getNavController()
            Scaffold (
                Modifier.background(Color.Black),
            ){ innerPadding ->
                Box(
                    Modifier
                        .background(Color.Black)
                        .padding(innerPadding)
                        .fillMaxSize()

                ) {
                    MyNavHostController(
                        navController = navController
                    )

                }
            }
        }
    }

}


