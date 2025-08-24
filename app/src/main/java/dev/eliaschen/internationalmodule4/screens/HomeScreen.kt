package dev.eliaschen.internationalmodule4.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dev.eliaschen.internationalmodule4.LocalNavController
import dev.eliaschen.internationalmodule4.R
import dev.eliaschen.internationalmodule4.models.Screen
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryBeige
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryBlack
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryDark
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryGray
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryYellow
import dev.eliaschen.internationalmodule4.ui.theme.optima
import dev.eliaschen.internationalmodule4.ui.theme.playFair
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.math.roundToInt

@Composable
fun HomeScreen() {
    val device = LocalConfiguration.current
    val nav = LocalNavController.current

    val title = "Experience Art"
    val content =
        "We are thrilled to invite you to join us for an extraordinary event that will immerse you in the world of art."
    val screenOpacity = remember { Animatable(0f) }
    val bodyMask = remember { Animatable(0f) }
    var titleTyping by remember { mutableStateOf(-1) }
    var contentTyping by remember { mutableStateOf(-1) }
    var showExploreButton by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(500L)
        screenOpacity.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500, easing = LinearEasing)
        )
        bodyMask.animateTo(
            targetValue = (device.screenHeightDp.dp - 350.dp).value,
            animationSpec = tween(2000, easing = LinearEasing)
        )
        while (isActive) {
            if (titleTyping < title.length - 1) titleTyping++
            else break
            delay(50L)
        }
        while (isActive) {
            if (contentTyping < content.length - 1) contentTyping++
            else break
            delay(20L)
        }
        showExploreButton = true
    }

    Box(
        modifier = Modifier
            .alpha(screenOpacity.value)
            .background(PrimaryDark)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .offset(0.dp, bodyMask.value.dp)
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.0f to Color.Transparent,
                        0.05f to PrimaryDark.copy(alpha = 0.5f),
                        0.1f to PrimaryDark.copy(alpha = 0.7f),
                        0.2f to PrimaryDark
                    )
                )
                .zIndex(10f)
                .align(Alignment.BottomCenter)
        )
        Box(
            Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(70.dp)
            )
        }
        val borderRadius = 500.dp
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .scale(0.8f)
                .border(
                    2.dp,
                    color = PrimaryYellow,
                    shape = RoundedCornerShape(topEnd = borderRadius, topStart = borderRadius)
                )
                .padding(30.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(topEnd = borderRadius, topStart = borderRadius))
        ) {
            Image(
                painter = painterResource(R.drawable.louvre),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(340.dp)
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(230.dp)
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .padding(horizontal = 50.dp)
                .zIndex(11f),
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    title.slice(0..titleTyping),
                    fontSize = 30.sp,
                    color = PrimaryBeige,
                    textAlign = TextAlign.Center, fontFamily = playFair
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    content.slice(0..contentTyping),
                    textAlign = TextAlign.Center,
                    color = PrimaryGray,
                    fontSize = 15.sp,
                )
            }
            AnimatedVisibility(
                showExploreButton,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearEasing
                    )
                ) + slideInVertically(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearEasing
                    )
                ) { it / 4 },
                modifier = Modifier.align(
                    Alignment.BottomCenter
                )
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = PrimaryYellow),
                    modifier = Modifier.navigationBarsPadding(),
                    shape = CircleShape,
                    onClick = { nav.navTo(Screen.Explore) }
                ) {
                    Text(
                        "Explore Now",
                        color = PrimaryDark,
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp),
                        fontSize = 20.sp, fontFamily = playFair
                    )
                }
            }
        }
    }
}