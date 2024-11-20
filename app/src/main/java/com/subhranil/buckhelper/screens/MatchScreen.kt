package com.subhranil.buckhelper.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.subhranil.buckhelper.BuckViewModel
import com.subhranil.buckhelper.components.Bullet
import com.subhranil.buckhelper.components.BurnerActionButton
import com.subhranil.buckhelper.components.ShowDataInOneLIne
import com.subhranil.buckhelper.components.ShowDataInTwoLine
import com.subhranil.buckhelper.ui.theme.Blank
import com.subhranil.buckhelper.ui.theme.Live

@Composable
fun MatchScreen(navController: NavHostController, viewModel: BuckViewModel) {
    val context = LocalContext.current
    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            BurnerActionButton {
//                Toast.makeText(context, "Burner", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.AddBurnerInfo.route)
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(20.dp, 0.dp, 20.dp, 0.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Bullet(true, viewModel.live, viewModel.round, viewModel.roundDetails)
                Spacer(modifier = Modifier.width(10.dp))
                Bullet(false, viewModel.blank, viewModel.round, viewModel.roundDetails)
            }
            Spacer(modifier = Modifier.height(10.dp))
            ShowDataInTwoLine(
                "Probability of live bullet",
                "${viewModel.getLiveProbability()} -> ${viewModel.getLiveProbabilityPercentage()}%",
                percentage = viewModel.getLiveProbability()
            )
            Spacer(modifier = Modifier.height(10.dp))
            ShowDataInOneLIne("Round", "${viewModel.round.intValue}")
            Spacer(modifier = Modifier.height(10.dp))
            ShowDataInOneLIne(
                "This Round Has",
                viewModel.burnerDetails[viewModel.round.intValue]?.let { if (it) "Live" else "Blank" }
                    ?: "Unknown",
                borderColor = viewModel.burnerDetails[viewModel.round.intValue]?.let { if (it) Live else Blank }
            )
            Spacer(modifier = Modifier.height(10.dp))
            RoundDetails(viewModel)
        }
    }
}

@Composable
private fun RoundDetails(viewModel: BuckViewModel) {
    Text(
        text = "Round History",
        fontSize = 25.sp,
        modifier = Modifier.padding(10.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    for (detail in viewModel.roundDetails) {
        ShowDataInOneLIne(
            "Round ${detail.key}",
            value = if (detail.value) "Live" else "Blank"
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}








