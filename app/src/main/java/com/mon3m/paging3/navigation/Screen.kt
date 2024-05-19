package com.mon3m.paging3.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
}