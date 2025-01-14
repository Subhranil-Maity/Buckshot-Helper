package com.subhranil.buckhelper.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.subhranil.buckhelper.ui.Blank
import com.subhranil.buckhelper.ui.Live

@Composable
fun RoundButton(
    index: Int,
    text: String = "",
    color: Color,
    round: Int,
    onClicked: (Int) -> Unit
) {
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

@Composable
fun BulletButton(index: Int, current: Int, color: Color, onClicked: (Int) -> Unit) {
    val shape = CircleShape
    val color = if (index <= current) color else Color.Black
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
            text = index.toString(),
            color = Color.White,
        )
    }
}
@Composable
fun Bullet(
    isLive: Boolean,
    numberOfBullets: MutableState<Int>,
    round: MutableState<Int>,
    roundDetails: SnapshotStateMap<Int, Boolean>
){
    val color = if (isLive) Live else Blank
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
                roundDetails[round.value] = isLive
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
