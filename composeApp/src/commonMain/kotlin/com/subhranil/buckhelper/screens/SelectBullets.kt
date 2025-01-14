package com.subhranil.buckhelper.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.subhranil.buckhelper.di.GlobalResources
import com.subhranil.buckhelper.ui.Blank
import com.subhranil.buckhelper.ui.Live
import com.subhranil.buckhelper.ui.components.BulletButton
import com.subhranil.buckhelper.ui.components.RoundButton
import kotlinx.serialization.Serializable
import kotlin.reflect.KFunction1


@Composable
fun SelectBullet(state: GlobalState, onAction: (GlobalActions) -> Unit) {
    val navController = GlobalResources.getNavController()

    var live by remember { mutableIntStateOf(0) }
    var blank by remember { mutableIntStateOf(0) }

    AnimatedContent(live) {
        if (live == 0) {
            SelectBulletFragment(
                modifier = Modifier.fillMaxSize(),
                bulletType = BulletType.LIVE,
                selected = live
            ) {
                live = it
            }
        } else if (blank == 0) {
            SelectBulletFragment(
                modifier = Modifier.fillMaxSize(),
                bulletType = BulletType.BLANK,
                selected = blank
            ) {
                blank = it
            }

        } else {
            onAction(GlobalActions.Reset(live, blank))
            onAction(GlobalActions.Play)
        }
    }
}

@Composable
fun SelectBulletFragment(
    modifier: Modifier = Modifier,
    bulletType: BulletType,
    selected: Int,
    onClick: (Int) -> Unit
) {
    Column {
        if (bulletType == BulletType.LIVE) {
            Text("Select Live", style = MaterialTheme.typography.titleLarge)
        } else {
            Text("Select Blank", style = MaterialTheme.typography.titleLarge)
        }
        Row(modifier = modifier) {
            HalfBulletSelect(1..5, selected, bulletType, onClick)
            Spacer(Modifier.weight(0.01f))
            HalfBulletSelect(6..10, selected, bulletType, onClick)
        }
    }


}

@Composable
fun RowScope.HalfBulletSelect(
    range: IntRange,
    selected: Int,
    bulletType: BulletType,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.weight(1f)
    ) {
        for (i in range) {
            BulletButton(
                index = i,
                current = selected,
                color = if (BulletType.LIVE == bulletType) Live else Blank,
                onClicked = onClick
            )
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Serializable
object SelectBulletRoute