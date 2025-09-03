package dev.eliaschen.secondinternationalmodule4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import dev.eliaschen.secondinternationalmodule4.models.NavController
import dev.eliaschen.secondinternationalmodule4.models.Screen
import dev.eliaschen.secondinternationalmodule4.screens.ArtistsScreen
import dev.eliaschen.secondinternationalmodule4.screens.ExhibitScreen
import dev.eliaschen.secondinternationalmodule4.screens.ExploreScreen
import dev.eliaschen.secondinternationalmodule4.screens.HomeScreen
import dev.eliaschen.secondinternationalmodule4.screens.TicketingScreen
import dev.eliaschen.secondinternationalmodule4.ui.theme.SecondInternationalmodule4Theme
import dev.eliaschen.secondinternationalmodule4.ui.theme.dark

val LocalNavController = compositionLocalOf<NavController> { error("navController") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondInternationalmodule4Theme {
                val nav = ViewModelProvider(this)[NavController::class.java]

                CompositionLocalProvider(LocalNavController provides nav) {
                    BackHandler {
                        if (nav.navStack.size > 1) {
                            nav.pop()
                        } else {
                            finish()
                        }
                    }
                    when (nav.currentNav) {
                        Screen.Home -> HomeScreen()
                        Screen.Explore -> ExploreScreen()
                        Screen.Ticketing -> TicketingScreen()
                        Screen.Artists -> ArtistsScreen()
                        Screen.Exhibit -> ExhibitScreen()
                        else -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(dark),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Screen ${nav.currentNav} not found")
                            }
                        }
                    }
                }
            }
        }
    }
}