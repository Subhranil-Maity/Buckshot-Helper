package com.subhranil.buckhelper.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.subhranil.buckhelper.BuckViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: BuckViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SelectBullet.route
//        startDestination = Screen.AddBurnerInfo.route
    ) {
        composable(Screen.SelectBullet.route) {
            SelectBullet(navController, viewModel)
        }
        composable(
            Screen.Match.route,
        ) {
            MatchScreen(navController, viewModel)
        }
        composable(
            Screen.AddBurnerInfo.route,
        ) {
            AddBurnerInfoScreen(navController, viewModel)
        }
    }
}