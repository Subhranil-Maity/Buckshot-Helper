package com.subhranil.buckhelper.screens

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import com.subhranil.buckhelper.di.GlobalResources
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class GlobalState(
    var live: Int = 0,
    var blank: Int = 0,
    var round: Int = 1,
    var roundDetails: SnapshotStateMap<Int, BulletType> = mutableStateMapOf(),
    var burnerDetails: SnapshotStateMap<Int, BulletType> = mutableStateMapOf()
){
    fun getLiveProbability(): Float {
        val live = live
        val blank = blank
        if (live == 0 && blank == 0) return 0f
        return live.toFloat() / (live + blank)
    }
    fun getLiveProbabilityPercentage(): Float {
        return getLiveProbability() * 100
    }
}
enum class BulletType {
    LIVE, BLANK
}

sealed interface GlobalActions{
    data class Decrease(val bulletType: BulletType): GlobalActions
    data class AddBurnerDetails(val round: Int, val bulletType: BulletType): GlobalActions
    data class AddRoundDetails(val round: Int, val bulletType: BulletType): GlobalActions
    data class Reset(val live: Int, val blank: Int): GlobalActions
    data object Play: GlobalActions
    data object AddBurner: GlobalActions
}

class MainViewModel: ViewModel() {
    private val _globalState = MutableStateFlow(GlobalState())
    val globalState = _globalState.asStateFlow()

    fun doAction(action: GlobalActions) {
        when(action) {
            is GlobalActions.Decrease -> decrease(action.bulletType)
            is GlobalActions.AddBurnerDetails -> addBurnerDetails(action.round, action.bulletType)
            is GlobalActions.AddRoundDetails -> addRoundDetails(action.round, action.bulletType)
            is GlobalActions.Reset -> reset(action.live, action.blank)
            GlobalActions.AddBurner -> GlobalResources.getNavController().navigate(AddBurnerScreenRoute)
            GlobalActions.Play -> GlobalResources.getNavController().navigate(MainScreenRoute)
        }
    }

    private fun decrease(bulletType: BulletType) {
        if (bulletType == BulletType.LIVE && _globalState.value.live == 0) return
        if (bulletType == BulletType.BLANK && _globalState.value.blank == 0) return
        when(bulletType) {
            BulletType.LIVE -> decrementLive()
            BulletType.BLANK -> decrementBlank()
        }
        addRoundDetails(
            _globalState.value.round,
            bulletType
        )
        incrementRound()
    }

    private fun addBurnerDetails(round: Int, bulletType: BulletType) {
        _globalState.update {
            it.copy(
                burnerDetails = it.burnerDetails.apply {
                    put(round, bulletType)
                }
            )
        }
        GlobalResources.getNavController().navigateUp()
    }

    private fun decrementBlank() {
        _globalState.update {
            it.copy(
                blank = it.blank - 1
            )
        }
    }

    private fun decrementLive() {
        _globalState.update {
            it.copy(
                live = it.live - 1
            )
        }
    }

    private fun incrementRound() {
        _globalState.update {
            it.copy(
                round = it.round + 1
            )
        }
    }
    private fun addRoundDetails(round: Int, bulletType: BulletType) {
        _globalState.update {
            it.copy(
                roundDetails = it.roundDetails.apply {
                    put(round, bulletType)
                }
            )
        }
    }

    private fun reset(live: Int, blank: Int) {
        _globalState.update {
            it.copy(
                live = live,
                blank = blank,
                round = 1,
                roundDetails = mutableStateMapOf(),
                burnerDetails = mutableStateMapOf()
            )
        }
        println("${_globalState.value.live}")
    }


}