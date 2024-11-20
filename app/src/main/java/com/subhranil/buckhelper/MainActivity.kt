package com.subhranil.buckhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.subhranil.buckhelper.screens.SetupNavGraph
import com.subhranil.buckhelper.ui.theme.BuckHelperTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    private val viewModel by viewModels<BuckViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuckHelperTheme {
                navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    viewModel
                )
            }
        }
    }
}



