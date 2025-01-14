package com.subhranil.buckhelper.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.subhranil.buckhelper.ui.Live
import com.subhranil.buckhelper.ui.components.BulletButton
import kotlinx.serialization.Serializable


@Composable
fun AddBurnerScreen(
    state: GlobalState,
    onActions: (GlobalActions) -> Unit
) {
    var bulletType by remember { mutableStateOf<BulletType?>(null) }
    var round by remember { mutableStateOf<Int?>(null) }
    if (round == null) {
        SelectRound(
            modifier = Modifier.fillMaxSize(),
        ) {
            round = it
        }
    }
    else if (bulletType == null) {
        Column {
            WhichTypeOfButton(BulletType.LIVE){
                bulletType = it
            }
            Spacer(Modifier.height(10.dp))
            WhichTypeOfButton(BulletType.BLANK){
                bulletType = it
            }
        }
    }
    else{
        onActions(GlobalActions.AddBurnerDetails(round!!, bulletType!!))
        bulletType = null
        round = null
    }
}

@Composable
fun WhichTypeOfButton(
    bulletType: BulletType,
    OnClicked: (BulletType) -> Unit
) {
    val shape = CircleShape
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(2.dp, Color.White, shape = shape),
        onClick = {
            OnClicked(bulletType)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
        ),

        ) {
        Text(
            text = if (bulletType == BulletType.LIVE) "Live" else "Blank",
            color = Color.White,
        )
    }
}

@Composable
fun SelectRound(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    Row() {
        HalfBulletSelect(
            1..5,
            -1,
            BulletType.LIVE,
            onClick
        )
        HalfBulletSelect(
            6..10,
            -1,
            BulletType.LIVE,
            onClick
        )
    }
}


@Serializable
object AddBurnerScreenRoute