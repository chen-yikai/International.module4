package dev.eliaschen.secondinternationalmodule4.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.eliaschen.secondinternationalmodule4.R
import dev.eliaschen.secondinternationalmodule4.ui.theme.dark
import dev.eliaschen.secondinternationalmodule4.ui.theme.yellow
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.absoluteValue

enum class Dir {
    Left, Right, None
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExhibitScreen() {
    val context = LocalContext.current
    val artworks = remember { mutableStateListOf<Artwork>() }
    val artworkImage = listOf(
        Pair(R.drawable.mona_lisa, bottomRounded),
        Pair(R.drawable.lady_ermine, topRounded),
        Pair(R.drawable.litta_madonna, bottomRounded)
    )

    LaunchedEffect(Unit) {
        val list = context.assets.open("artworks.json").let {
            BufferedReader(InputStreamReader(it)).readText()
        }.let {
            Gson().fromJson<List<Artwork>>(it, object : TypeToken<List<Artwork>>() {}.type)
        }
        artworks.clear()
        artworks.addAll(list)
    }

    val pagerState = rememberPagerState { artworks.size }

    var currentComment by remember { mutableStateOf("") }
    var nextComment by remember { mutableStateOf("") }
    var direction by remember { mutableStateOf(Dir.None) }
    var currentPage by remember { mutableIntStateOf(0) }
    var level by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(pagerState.currentPageOffsetFraction) {
        if (pagerState.currentPageOffsetFraction == 0f) {
            currentPage = pagerState.currentPage
            currentComment = artworks[currentPage].comment
        }
        if (currentPage == pagerState.currentPage) {
            when {
                pagerState.currentPageOffsetFraction > 0f -> direction = Dir.Right
                pagerState.currentPageOffsetFraction < 0f -> direction = Dir.Left
                pagerState.currentPageOffsetFraction == 0f -> direction = Dir.None
            }
        }
        nextComment = when (direction) {
            Dir.Right -> artworks[currentPage + 1].comment
            Dir.Left -> artworks[currentPage - 1].comment
            else -> artworks[currentPage].comment
        }
        level =
            if (pagerState.currentPageOffsetFraction >= 0) pagerState.currentPageOffsetFraction else 1f - pagerState.currentPageOffsetFraction.absoluteValue
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(dark)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .height(150.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .navigationBarsPadding(), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.quote),
                contentDescription = null,
                alpha = 0.3f,
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.TopStart)
                    .offset(x = (-10).dp, y = (-5).dp),
            )
            when (direction) {
                Dir.Left -> {
                    Text(
                        nextComment,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .offset(y = (level * 20f).dp)
                            .alpha(1 - level)
                    )
                    Text(
                        currentComment, textAlign = TextAlign.Center,
                        modifier = Modifier
                            .offset(y = ((1 - level) * 20f).dp)
                            .alpha(level)
                    )
                }

                Dir.Right -> {
                    Text(
                        currentComment,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .offset(y = (level * 20f).dp)
                            .alpha(1 - level)
                    )
                    Text(
                        nextComment, textAlign = TextAlign.Center,
                        modifier = Modifier
                            .offset(y = ((1 - level) * 20f).dp)
                            .alpha(level)
                    )
                }

                Dir.None -> {
                    Text(
                        currentComment,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center), pageSpacing = (-50).dp
        ) { page ->
            val artwork = artworks[page]
            val (image, shape) = artworkImage[page]
            val rotation =
                -(pagerState.currentPage - page + pagerState.currentPageOffsetFraction) * 10f

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(top = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = shape, modifier = Modifier
                        .rotate(rotation)
                        .width(300.dp)
                        .height(500.dp)
                        .align(Alignment.TopCenter)
                ) {
                    LazyColumn(reverseLayout = shape == topRounded, userScrollEnabled = false) {
                        item {
                            Row(
                                modifier = Modifier
                                    .height(90.dp)
                                    .background(yellow)
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(artwork.title, color = dark, fontSize = 25.sp)
                                    Text(
                                        "${artwork.years} ${artwork.bornAt}",
                                        color = Color.Black.copy(0.3f)
                                    )
                                }
                                IconButton(
                                    onClick = {},
                                    colors = IconButtonDefaults.iconButtonColors(containerColor = dark),
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.arrow_outward),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier
                                            .padding(9.dp)
                                    )
                                }
                            }
                        }
                        item {
                            Image(
                                painter = painterResource(image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(420.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}