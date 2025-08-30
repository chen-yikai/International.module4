package dev.eliaschen.rebuildinternationalmodule4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import dev.eliaschen.rebuildinternationalmodule4.models.NavController
import dev.eliaschen.rebuildinternationalmodule4.models.Screen
import dev.eliaschen.rebuildinternationalmodule4.screens.ArtistsScreen
import dev.eliaschen.rebuildinternationalmodule4.screens.ExhibitScreen
import dev.eliaschen.rebuildinternationalmodule4.screens.ExploreScreen
import dev.eliaschen.rebuildinternationalmodule4.screens.HomeScreen
import dev.eliaschen.rebuildinternationalmodule4.screens.TicketingScreen
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Dark
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.RebuildInternationalmodule4Theme

val LocalNavController = compositionLocalOf<NavController> { error("navController") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RebuildInternationalmodule4Theme {
                val navController = ViewModelProvider(this)[NavController::class.java]

                CompositionLocalProvider(LocalNavController provides navController) {
                    BackHandler {
                        if (navController.navStack.size > 1) {
                            navController.pop()
                        } else {
                            finish()
                        }
                    }
                    when (navController.currentNav) {
                        Screen.Home -> HomeScreen()
                        Screen.Explore -> ExploreScreen()
                        Screen.Ticketing -> TicketingScreen()
                        Screen.Artists -> ArtistsScreen()
                        Screen.Exhibit -> ExhibitScreen()
                        else -> {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(Dark),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    "Screen ${navController.currentNav.name} not found"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}