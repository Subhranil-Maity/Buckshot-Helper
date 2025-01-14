package com.subhranil.buckhelper.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.subhranil.buckhelper.DisplayType
import com.subhranil.buckhelper.di.GlobalResources
import com.subhranil.buckhelper.isLandScapeOrPortrait
import com.subhranil.buckhelper.ui.Blank
import com.subhranil.buckhelper.ui.Live
import com.subhranil.buckhelper.ui.components.AddBurnerActionButton
import com.subhranil.buckhelper.ui.components.ShowDataInOneLIne
import com.subhranil.buckhelper.ui.components.ShowDataInTwoLine
import kotlinx.serialization.Serializable

@Composable
fun MainScreen(state: GlobalState, onAction: (GlobalActions) -> Unit) {
    val navController = GlobalResources.getNavController()
    val displayType = isLandScapeOrPortrait()
    Scaffold(
        floatingActionButton = {
            AddBurnerActionButton {
                onAction(GlobalActions.AddBurner)
            }
        },
        modifier = Modifier.background(androidx.compose.ui.graphics.Color.Black)
    ) { innerPadding ->
        AnimatedContent(displayType) {

            Column(
                modifier = Modifier.fillMaxSize()
                    .background(Color.Black)
                    .padding(innerPadding)
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLandScapeOrPortrait() == DisplayType.PORTRAIT) {
                    NormalPortraitLayout(state = state, onAction = onAction)
                } else {
                    NormalLandscapeLayout(state = state, onAction = onAction)
                }
            }
        }
    }


}


@Composable
fun NormalLandscapeLayout(state: GlobalState, onAction: (GlobalActions) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier.weight(1f)
        ) {
            AllDetails(state)
        }
        NetBulletButtons(state, onAction, Modifier.fillMaxHeight().weight(1f))

    }
}

@Composable
fun NormalPortraitLayout(
    state: GlobalState,
    onAction: (GlobalActions) -> Unit
) {
    NetBulletButtons(state, onAction, Modifier.fillMaxWidth())
    Spacer(Modifier.height(10.dp))
    AllDetails(state)

}

@Composable
fun AllDetails(state: GlobalState) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
    ) {
        val bRound  = state.burnerDetails[state.round]
        val bType = bRound.let {
            when (it) {
                null -> return@let "Unknown"
                BulletType.LIVE -> "Live"
                else -> "Blank"
            }
        }
        val color = when(bRound){
            null -> null
            BulletType.LIVE -> Live
            else -> Blank
        }
        ShowDataInTwoLine(
            key = "Probability of Live",
            value = "${state.getLiveProbabilityPercentage()}%",
            percentage = state.getLiveProbability(),
        )
        Spacer(Modifier.height(10.dp))
        ShowDataInOneLIne(key = "Round", value = state.round.toString())
        Spacer(Modifier.height(10.dp))
        ShowDataInOneLIne(
            key = "This Round Has",
            value = bType,
            borderColor = color
        )
        Spacer(modifier = Modifier.height(10.dp))
        RoundDetails(state)

    }
}

@Composable
fun RoundDetails(state: GlobalState) {
    Text(
        text = "Round History",
        fontSize = 25.sp,
        modifier = Modifier.padding(10.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    for (detail in state.roundDetails) {
        ShowDataInOneLIne(
            "Round ${detail.key}",
            value = if (detail.value == BulletType.LIVE) "Live" else "Blank"
        )
        Spacer(modifier = Modifier.height(10.dp))
    }

}

@Composable
fun NetBulletButtons(
    state: GlobalState,
    onAction: (GlobalActions) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DecreaseButtonForBulletContents(state, bulletType = BulletType.LIVE, onAction)
        Spacer(Modifier.width(10.dp))
        DecreaseButtonForBulletContents(state, bulletType = BulletType.BLANK, onAction)
    }
}

@Composable
fun DecreaseButtonForBulletContents(
    state: GlobalState,
    bulletType: BulletType,
    onAction: (GlobalActions) -> Unit
) {
    val text = if (bulletType == BulletType.LIVE) "Live" else "Blank"
    val bullets = if (bulletType == BulletType.LIVE) state.live else state.blank

    Box(
        modifier = Modifier.width(100.dp)
            .height(100.dp)
            .clickable {
                onAction(GlobalActions.Decrease(bulletType))
            }.background(
                color = if (bulletType == BulletType.LIVE) Live else Blank,
                shape = RoundedCornerShape(24.dp)
            ),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = text, style = MaterialTheme.typography.headlineLarge)
            Text(text = bullets.toString(), style = MaterialTheme.typography.headlineLarge)
        }
    }

}


@Serializable
object MainScreenRoute