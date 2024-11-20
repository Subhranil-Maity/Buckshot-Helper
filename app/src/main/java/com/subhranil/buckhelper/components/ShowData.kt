package com.subhranil.buckhelper.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.subhranil.buckhelper.ui.theme.Blank
import com.subhranil.buckhelper.ui.theme.Live
import com.subhranil.buckhelper.utils.tintColor
import kotlin.math.abs

@Composable
fun ShowDataInTwoLine(key: String, value: String, percentage: Float = 1f){
    var color = Color(24, 26, 27)
    color = if(percentage == 1f){
        color
    }else if (percentage < 0.5f) {
        tintColor(Blank, color, percentage * 2)
    } else {
        tintColor(Live, color, abs(percentage - 1) * 2)
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
fun ShowDataInOneLIne(key: String, value: String, borderColor: Color? = null){
    var color = Color(24, 26, 27)
    if(borderColor != null){
        color = tintColor(color, borderColor, 0.5f)
    }
    Row(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color, shape = CircleShape)
            .clip(CircleShape)
            .padding(20.dp, 0.dp, 20.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(text = key, fontSize = 20.sp)
        Text(text = value, fontSize = 20.sp)
    }
}