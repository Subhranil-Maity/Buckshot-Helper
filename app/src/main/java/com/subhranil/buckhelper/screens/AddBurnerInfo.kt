package com.subhranil.buckhelper.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.subhranil.buckhelper.BuckViewModel
import com.subhranil.buckhelper.components.AddBurnerActionButton
import com.subhranil.buckhelper.ui.theme.Blank
import com.subhranil.buckhelper.ui.theme.Live

@Composable
fun AddBurnerInfoScreen(
    navController: NavHostController,
    viewModel: BuckViewModel
) {
    var round  = remember { mutableIntStateOf(viewModel.round.intValue) }
    var typeOfBullet = remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            AddBurnerActionButton {
                viewModel.addBurnerDetails(round.intValue, typeOfBullet.value)
                navController.popBackStack()
            }
        }
    ){ innerPadding ->
        Column (
            Modifier
                .padding(innerPadding)
                .padding(20.dp, 0.dp, 20.dp, 0.dp)
        ){
            Text(
                text = "Add Burner Info",
                fontSize = 25.sp,
                modifier = Modifier.padding(10.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())

            ){
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ){
                    for (i in 1..10 step 2) {
                        RoundButton(
                            index = i,
                            color = if (typeOfBullet.value) Live else Blank,
                            round = round.intValue
                        ) {
                            round.intValue = it
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ){
                    for (i in 2..10 step 2) {
                        RoundButton(
                            index = i,
                            color = if (typeOfBullet.value) Live else Blank,
                            round = round.intValue
                        ) {
                            round.intValue = it
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
            Text(
                    text  = "Live Or Blank",
            fontSize = 25.sp,
            modifier = Modifier.padding(10.dp)
            )
            Column (
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                RoundButton(1, "Blank", Blank, if(typeOfBullet.value) 2 else 1) {
                    typeOfBullet.value = false
                }
                Spacer(modifier = Modifier.height(10.dp))
                RoundButton(1, "Live", Live, if(typeOfBullet.value) 1 else 2) {
                    typeOfBullet.value = true
                }
            }
        }

    }
}

@Composable
fun RoundButton(index: Int, text: String = "" ,color: Color, round: Int, onClicked: (Int) -> Unit) {
    val shape = CircleShape
    val color = if (round == index) color else Color.Black
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(2.dp, Color.White, shape = shape),
        onClick = { onClicked(index) },
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),

        ) {
        Text(
            text = text.ifEmpty { index.toString() },
            color = Color.White,
        )
    }
}