package dev.eliaschen.internationalmodule4.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NavController : ViewModel() {
    private val initNav = Screen.Artists
    var currentNav by mutableStateOf(initNav)
    val navStack = mutableListOf(initNav)

    fun navTo(screen: Screen) {
        navStack.add(currentNav)
        currentNav = screen
    }

    fun pop() {
        if (navStack.size > 1) {
            navStack.removeLast()
            currentNav = navStack.last()
        }
    }
}

enum class Screen {
    Home, Explore, Ticketing, Artists, Exhibit
}