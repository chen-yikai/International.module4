package dev.eliaschen.secondinternationalmodule4.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dev.eliaschen.secondinternationalmodule4.LocalNavController
import dev.eliaschen.secondinternationalmodule4.R
import dev.eliaschen.secondinternationalmodule4.models.Screen
import dev.eliaschen.secondinternationalmodule4.ui.theme.beige
import dev.eliaschen.secondinternationalmodule4.ui.theme.dark
import dev.eliaschen.secondinternationalmodule4.ui.theme.playFair
import dev.eliaschen.secondinternationalmodule4.ui.theme.yellow
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun HomeScreen() {
    val device = LocalConfiguration.current
    val nav = LocalNavController.current
    val opacityMovement = remember { Animatable(0f) }
    val maskMovement = remember { Animatable(0f) }
    var showButton by remember { mutableStateOf(false) }

    val title = "Experience Art"
    val content =
        "We are thrilled to invite you to join us for an extraordinary event that will immerse you in the world of art."
    var titleType by remember { mutableStateOf(-1) }
    var contentType by remember { mutableStateOf(-1) }

    LaunchedEffect(Unit) {
        delay(500)
        opacityMovement.animateTo(1f, tween(durationMillis = 500, easing = LinearEasing))
        maskMovement.animateTo(490f, tween(durationMillis = 3000, easing = LinearEasing))
        while (true) {
            if (titleType + 2 > title.length) break
            titleType++
            delay(80)
        }
        while (true) {
            if (contentType + 2 > content.length) break
            contentType++
            delay(40)
        }
        showButton = true
    }

    Box(
        modifier = Modifier
            .alpha(opacityMovement.value)
            .fillMaxSize()
            .background(dark)
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(10f)
                .fillMaxWidth()
                .height(device.screenHeightDp.dp)
                .offset(y = (-75).dp)
                .offset(y = maskMovement.value.dp)
//                .border(1.dp, Color.Red)
                .background(
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        0.05f to dark.copy(0.5f),
                        0.1f to dark
                    )
                )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
            )
            Spacer(Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .border(3.dp, yellow, RoundedCornerShape(topEnd = 500.dp, topStart = 500.dp))
                    .padding(25.dp)
                    .clip(RoundedCornerShape(topEnd = 500.dp, topStart = 500.dp))
                    .width(300.dp)
            ) {
                Image(painter = painterResource(R.drawable.louvre), contentDescription = null)
            }
        }
        Column(
            modifier = Modifier
                .zIndex(11f)
                .padding(horizontal = 40.dp)
                .height(240.dp)
                .align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                title.slice(0..titleType),
                fontSize = 35.sp,
                fontFamily = playFair,
                color = beige,
            )
            Spacer(Modifier.height(10.dp))
            Text(
                content.slice(0..contentType),
                textAlign = TextAlign.Center
            )
        }
        AnimatedVisibility(
            showButton,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(11f)
                .align(Alignment.BottomCenter)
                .offset(y = (-40).dp),
            enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(
                animationSpec = tween(
                    700,
                    easing = LinearEasing
                )
            )
        ) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = yellow),
                modifier = Modifier,
                onClick = {nav.navTo(Screen.Explore)}) {
                Text(
                    "Explore Now",
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
                    fontSize = 20.sp, fontFamily = playFair, color = dark
                )
            }
        }
    }
}