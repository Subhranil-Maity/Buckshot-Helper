package com.subhranil.buckhelper.screens

import android.health.connect.datatypes.units.Percentage
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.subhranil.buckhelper.BuckViewModel
import kotlin.math.abs

@Composable
fun MatchScreen(navController: NavHostController, viewModel: BuckViewModel) {
    val context = LocalContext.current
    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            BurnerActionButton {
                Toast.makeText(context, "Burner", Toast.LENGTH_SHORT).show()
            }
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(20.dp, 0.dp, 20.dp, 0.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Bullet(true, viewModel.live, viewModel.round)
                Spacer(modifier = Modifier.width(10.dp))
                Bullet(false, viewModel.blank, viewModel.round)
            }
            Spacer(modifier = Modifier.height(10.dp))
            ShowDataInTwoLine(
                "Probability of live bullet",
                "${viewModel.getLiveProbability()} -> ${viewModel.getLiveProbabilityPercentage()}%",
                percentage = viewModel.getLiveProbability()
            )
            Spacer(modifier = Modifier.height(10.dp))
            ShowDataInOneLIne("Round", "${viewModel.round.intValue}")
        }
    }
}


@Composable
fun BurnerActionButton(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = { Icon(Icons.Filled.Call, "add burner data") },
        text = { Text(text = "Burner Info") },
    )
}

@Composable
fun ShowDataInOneLIne(key: String, value: String){
    Row(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(24, 26, 27), shape = CircleShape)
            .clip(CircleShape)
            .padding(20.dp, 0.dp, 20.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(text = key, fontSize = 20.sp)
        Text(text = value, fontSize = 20.sp)
    }
}
/**
 * Tints a primary color using a secondary color by a specified percentage.
 *
 * @param primary The primary color to be tinted.
 * @param secondary The secondary color to blend with.
 * @param tintPercentage The percentage of the tint (0.0 to 1.0).
 * @return A new color representing the blend.
 */
fun tintColor(primary: Color, secondary: Color, tintPercentage: Float): Color {
    val clampedPercentage = tintPercentage.coerceIn(0f, 1f)

    // Interpolate the RGBA components
    val red = (primary.red * (1 - clampedPercentage)) + (secondary.red * clampedPercentage)
    val green = (primary.green * (1 - clampedPercentage)) + (secondary.green * clampedPercentage)
    val blue = (primary.blue * (1 - clampedPercentage)) + (secondary.blue * clampedPercentage)
    val alpha = (primary.alpha * (1 - clampedPercentage)) + (secondary.alpha * clampedPercentage)

    return Color(red, green, blue, alpha)
}


@Composable
fun ShowDataInTwoLine(key: String, value: String, percentage: Float = 1f){
    var color = Color(24, 26, 27)
    color = if(percentage == 1f){
        color
    }else if (percentage < 0.5f) {
        tintColor(Color.Blue, color, percentage * 2)
    } else {
        tintColor(Color.Red, color, abs(percentage - 1) * 2)
    }

    Column(
        Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color, shape = CircleShape)
            .clip(CircleShape)
            .padding(20.dp, 5.dp, 20.dp, 0.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(text = key, fontSize = 20.sp)
        Text(text = value, fontSize = 20.sp)
    }
}


@Composable
fun Bullet(live: Boolean, numberOfBullets: MutableState<Int>, round: MutableState<Int>){
    val color = if (live) Color.Red else Color.Blue
    val hapticFeedback = LocalHapticFeedback.current
    Button(
        modifier = Modifier
            .height(200.dp)
            .width(150.dp)
            .border(1.dp, Color.Black)
        ,
        onClick = {
            if (numberOfBullets.value != 0) {
                numberOfBullets.value -= 1
                round.value += 1
                return@Button
            }
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
        },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(20.dp)
    ){
        Text(text = "${numberOfBullets.value}", Modifier.padding(10.dp))
    }
}