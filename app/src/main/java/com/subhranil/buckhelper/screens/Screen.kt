package com.subhranil.buckhelper.screens



sealed class Screen(val route: String){
    object SelectBullet: Screen("select_bullet_screen")
//    object Match: Screen("match_screen")
    object Match: Screen("match_screen")
    object AddBurnerInfo: Screen("add_burner_info_screen")
}