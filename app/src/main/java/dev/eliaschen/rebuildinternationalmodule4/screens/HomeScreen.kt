package dev.eliaschen.rebuildinternationalmodule4.screens

import android.hardware.lights.Light
import androidx.collection.mutableIntSetOf
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dev.eliaschen.rebuildinternationalmodule4.LocalNavController
import dev.eliaschen.rebuildinternationalmodule4.R
import dev.eliaschen.rebuildinternationalmodule4.models.Screen
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Beige
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Dark
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.LightGray
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Yellow
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.playFair
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun HomeScreen() {
    val nav = LocalNavController.current

    val bodyOpacity = remember { Animatable(0f) }
    val contentMask = remember { Animatable(-70f) }

    val titleText = "Experience Art"
    val contentText =
        "We are thrilled to invite you to join us for an extraordinary event that will immerse you in the world of art."
    var titleType by remember { mutableIntStateOf(-1) }
    var contentType by remember { mutableIntStateOf(-1) }
    var showNavButton by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1000)
        bodyOpacity.animateTo(
            1f,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
        )
        contentMask.animateTo(
            425f,
            animationSpec = tween(durationMillis = 4000, easing = LinearEasing)
        )
        while (isActive) {
            if (titleType + 2 > titleText.length) {
                break
            } else {
                titleType++
            }
            delay(90)
        }
        while (isActive) {
            if (contentType + 2 > contentText.length) {
                break
            } else {
                contentType++
            }
            delay(30)
        }
        showNavButton = true
    }

    Box(
        modifier = Modifier
            .alpha(bodyOpacity.value)
            .fillMaxSize()
            .background(Dark)
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .offset(y = contentMask.value.dp) // 400
                .zIndex(9f)
                .fillMaxSize()
//                .border(2.dp, Color.Red)
                .background(
                    Brush.verticalGradient(
                        0.0f to Color.Transparent,
                        0.1f to Dark
                    )
                )
        )
        Column(
            modifier = Modifier
                .zIndex(8f)
                .fillMaxWidth()
                .align(Alignment.TopCenter), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            val imageRadius = RoundedCornerShape(
                topEnd = 1000.dp,
                topStart = 1000.dp
            )
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .border(2.dp, Yellow, imageRadius)
                    .padding(25.dp)
                    .clip(imageRadius)
                    .height(410.dp)
            ) {
                Image(painter = painterResource(R.drawable.louvre), contentDescription = null)
            }
        }
        Column(
            modifier = Modifier
                .height(250.dp)
                .zIndex(10f)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 50.dp)
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                titleText.slice(0..titleType),
                fontFamily = playFair,
                fontSize = 35.sp,
                color = Beige,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(5.dp))
            Text(
                contentText.slice(0..contentType),
                textAlign = TextAlign.Center, lineHeight = 25.sp, color = LightGray
            )

        }
        AnimatedVisibility(
            showNavButton,
            enter = slideInVertically(
                initialOffsetY = { it / 4 },
                animationSpec = tween(durationMillis = 700)
            ) + fadeIn(animationSpec = tween(durationMillis = 700)),
            modifier = Modifier
                .align(
                    Alignment.BottomCenter
                )
                .zIndex(11f)
        ) {
            Card(
                onClick = { nav.navTo(Screen.Explore) },
                elevation = CardDefaults.elevatedCardElevation(10.dp),
                colors = CardDefaults.cardColors(containerColor = Yellow),
                shape = CircleShape,
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(bottom = 20.dp)
                    .zIndex(11f)
            ) {
                Text(
                    "Explore Now",
                    fontSize = 20.sp,
                    fontFamily = playFair,
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
                )
            }
        }
    }
}