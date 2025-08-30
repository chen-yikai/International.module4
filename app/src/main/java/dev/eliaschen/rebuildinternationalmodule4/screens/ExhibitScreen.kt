package dev.eliaschen.rebuildinternationalmodule4.screens

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.rebuildinternationalmodule4.R
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Dark
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Yellow
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.absoluteValue

data class ArtWork(
    val title: String,
    val year: String,
    val bornAt: String,
    val comment: String
)

enum class Pos {
    Top, Bottom
}

enum class Dir {
    Left, Right, None
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExhibitScreen() {
    val context = LocalContext.current
    val artworks = remember { mutableStateListOf<ArtWork>() }
    val pager = rememberPagerState { 3 }
    val paints = listOf(
        Pair(R.drawable.lady_ermine, Pos.Top),
        Pair(R.drawable.mona_lisa, Pos.Bottom),
        Pair(R.drawable.litta_madonna, Pos.Top)
    )

    LaunchedEffect(Unit) {
        context.assets.open("artworks.json").bufferedReader().readText().let { jsonText ->
            val array = JSONArray(jsonText)
            artworks.clear()
            artworks.addAll(List(array.length()) { index ->
                val item = array.getJSONObject(index)

                ArtWork(
                    title = item.getString("title"),
                    year = item.getString("years"),
                    bornAt = item.getString("born_at"),
                    comment = item.getString("comment")
                )
            })
        }
    }

    if (artworks.size == 3) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Dark)
        ) {
            var currentPage by remember { mutableIntStateOf(0) }
            var direction by remember { mutableStateOf(Dir.None) }
            var nextComment by remember { mutableStateOf("") }
            var currentComment by remember { mutableStateOf("") }
            var positiveOffset by remember { mutableFloatStateOf(0f) }

            LaunchedEffect(pager.currentPageOffsetFraction) {
                positiveOffset = pager.currentPageOffsetFraction.let {
                    if (it >= 0) {
                        it
                    } else {
                        1f - it.absoluteValue
                    }
                }
                if (pager.currentPageOffsetFraction == 0f) {
                    currentPage = pager.currentPage
                    direction = Dir.None
                    currentComment = artworks[currentPage].comment
                }

                if (direction == Dir.None) {
                    when {
                        pager.currentPageOffsetFraction < 0 -> direction = Dir.Left
                        pager.currentPageOffsetFraction > 0 -> direction = Dir.Right
                        pager.currentPageOffsetFraction == 0f -> direction = Dir.None
                    }
                }

                val nextCommentIndex = when (direction) {
                    Dir.Right -> currentPage + 1
                    Dir.Left -> currentPage - 1
                    else -> 0
                }

                nextComment = artworks[nextCommentIndex].comment
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Image(
                    painter = painterResource(R.drawable.quote),
                    contentDescription = null,
                    alpha = 0.3f, modifier = Modifier
                        .size(60.dp)
                        .offset(30.dp, (-30).dp)
                )
                when (direction) {
                    Dir.Left -> {
                        Text(
                            nextComment,
                            modifier = Modifier
                                .padding(horizontal = 50.dp)
                                .padding(bottom = 50.dp)
                                .align(Alignment.TopCenter)
                                .offset(y = (positiveOffset * 30f).dp)
                                .alpha(1f - positiveOffset),
                            textAlign = TextAlign.Center,
                            lineHeight = 25.sp
                        )
                        Text(
                            currentComment,
                            modifier = Modifier
                                .padding(horizontal = 50.dp)
                                .padding(bottom = 50.dp)
                                .align(Alignment.TopCenter)
                                .offset(y = 30.dp)
                                .offset(y = -(positiveOffset * 30f).dp)
                                .alpha(positiveOffset),
                            textAlign = TextAlign.Center,
                            lineHeight = 25.sp
                        )
                    }

                    Dir.Right -> {
                        Text(
                            currentComment,
                            modifier = Modifier
                                .padding(horizontal = 50.dp)
                                .padding(bottom = 50.dp)
                                .align(Alignment.TopCenter)
                                .offset(y = (positiveOffset * 30f).dp)
                                .alpha(1f - positiveOffset),
                            textAlign = TextAlign.Center,
                            lineHeight = 25.sp
                        )
                        Text(
                            nextComment,
                            modifier = Modifier
                                .padding(horizontal = 50.dp)
                                .padding(bottom = 50.dp)
                                .align(Alignment.TopCenter)
                                .offset(y = 30.dp)
                                .offset(y = -(positiveOffset * 30f).dp)
                                .alpha(positiveOffset),
                            textAlign = TextAlign.Center,
                            lineHeight = 25.sp
                        )
                    }

                    else -> {
                        Text(
                            currentComment,
                            modifier = Modifier
                                .padding(horizontal = 50.dp)
                                .padding(bottom = 50.dp)
                                .align(Alignment.TopCenter),
                            textAlign = TextAlign.Center,
                            lineHeight = 25.sp
                        )
                    }
                }
            }
            HorizontalPager(
                pager,
                pageSpacing = (-60).dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) { index ->
                val artwork = artworks[index]
                val (image, dir) = paints[index]
                val top = dir == Pos.Top
                val rotation = -(pager.currentPage - index + pager.currentPageOffsetFraction) * 10f

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .padding(top = 20.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .rotate(rotation)
                            .width(300.dp)
                            .height(500.dp),
                        shape = if (top) RoundedCornerShape(
                            bottomEnd = 500.dp,
                            bottomStart = 500.dp
                        ) else RoundedCornerShape(
                            topEnd = 500.dp,
                            topStart = 500.dp
                        )
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(image),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                                    .width(300.dp)
                                    .align(if (!top) Alignment.TopCenter else Alignment.BottomCenter)
                            )
                            Row(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .background(Yellow)
                                    .padding(horizontal = 20.dp, vertical = 25.dp)
                                    .align(if (top) Alignment.TopCenter else Alignment.BottomCenter),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(artwork.title, fontSize = 25.sp, color = Dark)
                                    Text("${artwork.year}, ${artwork.bornAt}", color = Color.Gray)
                                }
                                Card(
                                    shape = CircleShape,
                                    colors = CardDefaults.cardColors(containerColor = Dark)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.arrow_outward),
                                        contentDescription = null,
                                        modifier = Modifier.padding(10.dp),
                                        tint = Color.White
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
