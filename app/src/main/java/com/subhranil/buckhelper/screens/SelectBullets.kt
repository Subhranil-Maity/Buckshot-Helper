package com.subhranil.buckhelper.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.subhranil.buckhelper.BuckViewModel
import com.subhranil.buckhelper.components.BulletButton
import com.subhranil.buckhelper.components.GoToMatchActionButton
import com.subhranil.buckhelper.ui.theme.Blank
import com.subhranil.buckhelper.ui.theme.Live

@Composable
fun SelectBullet(navController: NavHostController, viewModel: BuckViewModel) {
    var live by remember { mutableIntStateOf(0) }
    var blank by remember { mutableIntStateOf(0) }
    Scaffold(
        floatingActionButton = {
            GoToMatchActionButton {
                viewModel.reset(live, blank)
                navController.navigate(Screen.Match.route)
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp, 0.dp, 20.dp, 0.dp)
        ) {
            Text(
                text = "Select Live Bullets",
                fontSize = 25.sp,
                modifier = Modifier.padding(10.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Column (
                    Modifier.fillMaxHeight()
                        .weight(1f)
                ){
                    for (i in 1..10 step 2) {
                        BulletButton(
                            i,
                            live,
                            Live
                        ) {
                            live = i
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column (
                    Modifier.fillMaxHeight()
                        .weight(1f)
                ){
                    for (i in 2..10 step 2) {
                        BulletButton(
                            i,
                            live,
                            Live
                        ) {
                            live = i
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
            Text(
                text = "Select Blank Bullets",
                fontSize = 25.sp,
                modifier = Modifier.padding(10.dp)
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Column (
                    Modifier.fillMaxHeight()
                        .weight(1f)
                ){
                    for (i in 1..10 step 2) {
                        BulletButton(
                            i,
                            blank,
                            Blank
                        ) {
                            blank = i
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column (
                    Modifier.fillMaxHeight()
                        .weight(1f)
                ){
                    for (i in 2..10 step 2) {
                        BulletButton(
                            i,
                            blank,
                            Blank
                        ) {
                            blank = i
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}






