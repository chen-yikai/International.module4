package dev.eliaschen.internationalmodule4.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.internationalmodule4.R
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryBlack
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryDark
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryGray
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryYellow
import org.json.JSONArray
import kotlin.math.absoluteValue

data class Artwork(
    val title: String,
    val years: String,
    val bornAt: String,
    val comment: String
)

enum class ArtworkStyle {
    ContentTop, ContentBottom
}

enum class Direction {
    None, Right, Left
}

@Composable
fun ExhibitScreen() {
    val context = LocalContext.current
    val artworks = remember { mutableStateListOf<Artwork>() }

    LaunchedEffect(Unit) {
        context.assets.open("artworks.json").bufferedReader().readText().let { text ->
            val array = JSONArray(text)
            artworks.clear()
            artworks.addAll(List(array.length()) { index ->
                val item = array.getJSONObject(index)
                Artwork(
                    title = item.getString("title"),
                    years = item.getString("years"),
                    bornAt = item.getString("born_at"),
                    comment = item.getString("comment")
                )
            })
        }
    }

    val artworkStyles =
        listOf(ArtworkStyle.ContentTop, ArtworkStyle.ContentBottom, ArtworkStyle.ContentTop)
    val artworkResource =
        listOf(R.drawable.mona_lisa, R.drawable.lady_ermine, R.drawable.litta_madonna)
    val pagerState = rememberPagerState { artworks.size }

    if (artworks.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryDark)
                .systemBarsPadding()
        ) {
            var currentComment by remember { mutableStateOf("") }
            var nextComment by remember { mutableStateOf("") }
            var currentPage by remember { mutableStateOf(0) }
            var direction by remember { mutableStateOf(Direction.None) }
            var positivePagerOffset by remember { mutableStateOf(0f) }

            LaunchedEffect(pagerState.currentPageOffsetFraction) {
                positivePagerOffset = pagerState.currentPageOffsetFraction.let {
                    if (it >= 0f) {
                        it
                    } else {
                        (1 - it.absoluteValue)
                    }
                }
                if (pagerState.currentPageOffsetFraction == 0.0f) {
                    direction = Direction.None
                    currentPage = pagerState.currentPage
                    currentComment = artworks[pagerState.currentPage].comment
                }
                if (direction == Direction.None) {
                    if (pagerState.currentPageOffsetFraction > 0f) {
                        direction = Direction.Right
                    } else if (pagerState.currentPageOffsetFraction < 0f) {
                        direction = Direction.Left
                    }
                }
                nextComment = when (direction) {
                    Direction.Right -> artworks[currentPage + 1].comment
                    Direction.Left -> artworks[currentPage - 1].comment
                    else -> ""
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 30.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Image(
                    painter = painterResource(R.drawable.quote),
                    contentDescription = null,
                    alpha = 0.1f, modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset((-10).dp, (-40).dp)
                        .size(70.dp)
                )
                Box {
                    when (direction) {
                        Direction.Right -> {
                            Text(
                                currentComment,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .offset(y = (positivePagerOffset * 20).dp)
                                    .alpha(1f - positivePagerOffset)
                            )
                            Text(
                                nextComment,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .offset(y = 20.dp)
                                    .offset(y = -(positivePagerOffset * 20).dp)
                                    .alpha(positivePagerOffset)
                            )
                        }

                        Direction.Left -> {
                            Text(
                                nextComment,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .offset(y = (positivePagerOffset * 20).dp)
                                    .alpha(1f - positivePagerOffset)
                            )
                            Text(
                                currentComment,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .offset(y = 20.dp)
                                    .offset(y = -(positivePagerOffset * 20).dp)
                                    .alpha(positivePagerOffset)
                            )
                        }

                        Direction.None -> Text(
                            currentComment,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .offset(y = (positivePagerOffset * 20).dp)
                                .alpha(1f - positivePagerOffset)
                        )
                    }

                }
            }

            HorizontalPager(
                pagerState,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                pageSpacing = (-60).dp,
            ) { index ->
                val rotation =
                    -(pagerState.currentPage - index + pagerState.currentPageOffsetFraction) * 10f
                val top = artworkStyles[index] == ArtworkStyle.ContentTop
                val resource = artworkResource[index]
                val artwork = artworks[index]

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp)
                        .rotate(rotation)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .size(310.dp, 500.dp)
                            .clip(
                                if (top) RoundedCornerShape(
                                    bottomEnd = 500.dp,
                                    bottomStart = 500.dp
                                ) else RoundedCornerShape(topStart = 500.dp, topEnd = 500.dp)
                            )
                    ) {
                        Image(
                            painter = painterResource(resource),
                            contentDescription = null, contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(400.dp)
                                .width(310.dp)
                                .align(if (top) Alignment.BottomCenter else Alignment.TopCenter)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .background(PrimaryYellow)
                                .padding(20.dp)
                                .align(if (top) Alignment.TopCenter else Alignment.BottomCenter),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(artwork.title, fontSize = 25.sp, color = PrimaryDark)
                                Text(
                                    "${artwork.years}, ${artwork.bornAt}",
                                    color = PrimaryBlack.copy(alpha = 0.5f)
                                )
                            }
                            Card(
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(containerColor = PrimaryDark)
                            ) {
                                Box(modifier = Modifier.padding(12.dp)) {
                                    Image(
                                        painter = painterResource(R.drawable.arrow_outward),
                                        contentDescription = null, modifier = Modifier.size(25.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}