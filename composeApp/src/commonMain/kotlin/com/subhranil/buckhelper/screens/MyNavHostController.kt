package com.subhranil.buckhelper.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MyNavHostController(navController: NavHostController) {
    val viewModel = koinViewModel<MainViewModel>()
    val state by viewModel.globalState.collectAsStateWithLifecycle()
    NavHost(
        navController = navController,
        startDestination = SelectBulletRoute
    ){
        composable<MainScreenRoute>{
            MainScreen(
                state = state,
                onAction = viewModel::doAction
            )
        }
        composable<SelectBulletRoute> {
            SelectBullet(
                state = state,
                onAction = viewModel::doAction
            )
        }
        composable<AddBurnerScreenRoute> {
            AddBurnerScreen(
                state = state,
                onActions = viewModel::doAction
            )
        }
    }
}