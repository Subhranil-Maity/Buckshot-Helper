package com.subhranil.buckhelper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel

class BuckViewModel: ViewModel() {
    var live = mutableIntStateOf(5)
        private set
    var blank = mutableIntStateOf(2)
        private set
    var round = mutableIntStateOf(1)
        private set
    var burner: SnapshotStateMap<Int, Boolean> = mutableStateMapOf()
        private set

    fun decrementLive() {
        live.intValue--
    }
    fun decrementBlank() {
        blank.intValue--
    }
    fun incrementRound() {
        round.intValue++
    }
    fun getLiveProbability(): Float {
        return live.intValue.toFloat() / (live.intValue + blank.intValue)
    }
    fun getLiveProbabilityPercentage(): Float {
        return getLiveProbability() * 100
    }
    fun reset(live: Int, blank: Int) {
        this.live.value = live
        this.blank.value = blank
        this.round.value = 0
    }
}