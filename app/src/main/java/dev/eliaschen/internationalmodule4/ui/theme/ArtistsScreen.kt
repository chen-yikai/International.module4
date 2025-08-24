package dev.eliaschen.internationalmodule4.ui.theme

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import dev.eliaschen.internationalmodule4.LocalNavController
import dev.eliaschen.internationalmodule4.R
import dev.eliaschen.internationalmodule4.models.Screen
import kotlin.math.roundToInt

data class Artist(
    val name: String,
    val lifeTime: String,
    val avatar: Int,
    val reverse: Boolean = false,
    val artwork: List<Pair<Int, Shape>>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistsScreen() {
    val context = LocalContext.current
    val nav = LocalNavController.current
    val bgBitmap =
        remember { BitmapFactory.decodeResource(context.resources, R.drawable.background) }
    val imageShader = ImageShader(bgBitmap.asImageBitmap(), TileMode.Repeated, TileMode.Repeated)
    val shaderBrush = remember { ShaderBrush(imageShader) }
    val scroller = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(shaderBrush)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .verticalScroll(scroller)
        ) {
            Spacer(Modifier.statusBarsPadding())
            Column(modifier = Modifier.padding(30.dp)) {
                Text(
                    "Explore the art of",
                    fontSize = 30.sp,
                    fontFamily = playFair,
                    color = PrimaryDark
                )
                Text(
                    "Renaissance",
                    fontSize = 45.sp,
                    fontFamily = playFair,
                    color = PrimaryYellow,
                    modifier = Modifier.offset(y = (-15).dp)
                )
                var searchText by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    Modifier.fillMaxWidth(),
                    placeholder = { Text("Type to search...", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = null, Modifier.size(25.dp)
                        )
                    }, trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.crop_free),
                            contentDescription = null, Modifier.size(25.dp)
                        )
                    }, shape = RectangleShape
                )
            }
            val tabItems = listOf("Artists", "Artworks")
            var selectedItem by remember { mutableStateOf(0) }
            PrimaryTabRow(
                containerColor = Color.Transparent,
                divider = { HorizontalDivider(color = PrimaryDark, thickness = 1.dp) },
                indicator = {
                    TabRowDefaults.PrimaryIndicator(
                        Modifier.tabIndicatorOffset(selectedItem),
                        height = 3.dp,
                        width = 60.dp,
                        color = PrimaryYellow
                    )
                },
                selectedTabIndex = selectedItem
            ) {
                tabItems.forEachIndexed { index, item ->
                    Tab(selected = true, onClick = {
                        selectedItem = index
                    }) {
                        Text(
                            item,
                            fontSize = 25.sp,
                            color = if (index == selectedItem) PrimaryYellow else Color.Gray,
                            modifier = Modifier
                                .weight(1f)
                                .padding(bottom = 10.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            val leonardo = listOf(
                Pair(R.drawable.mona_lisa, CircleShape), Pair(
                    R.drawable.lady_ermine,
                    RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp)
                ), Pair(R.drawable.litta_madonna, RectangleShape)
            )
            val michelangelo = listOf(
                Pair(
                    R.drawable.delphic_sibyl,
                    RoundedCornerShape(topEnd = 100.dp, topStart = 100.dp)
                ),
                Pair(
                    R.drawable.torment_of_saint_anthony,
                    CircleShape
                ),
                Pair(R.drawable.david, RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp))
            )
            val gustav = listOf(
                Pair(
                    R.drawable.the_kiss,
                    RoundedCornerShape(topEnd = 100.dp, topStart = 100.dp)
                ),
                Pair(
                    R.drawable.adele_bloch_bauer,
                    RectangleShape
                ),
                Pair(R.drawable.lady_with_fan, CircleShape)
            )
            val artist = listOf(
                Artist(
                    name = "Leonardo da Vinci", lifeTime = "1452 - 1519",
                    avatar = R.drawable.leonardo_da_vinci,
                    artwork = leonardo
                ),
                Artist(
                    name = "Michelangelo",
                    lifeTime = "1475 - 1564",
                    reverse = true,
                    artwork = michelangelo,
                    avatar = R.drawable.michelangelo
                ),
                Artist(
                    name = "Gustav Klimt",
                    lifeTime = "1862 - 1918",
                    artwork = gustav,
                    avatar = R.drawable.gustav_klimt
                ),
            )
            artist.forEachIndexed { index, item ->
                Column(
                    modifier = Modifier
                        .padding(vertical = 30.dp)
                        .fillMaxWidth()
                        .clickable(onClick = {
                            nav.navTo(Screen.Exhibit)
                        }, enabled = index == 0),
                ) {
                    if (!item.reverse) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 30.dp)
                        ) {
                            ArtistAvatar(item.avatar)
                            Spacer(Modifier.width(10.dp))
                            ArtistInfo(item.name, item.lifeTime)
                        }
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            ArtistInfo(item.name, item.lifeTime, reverse = true)
                            Spacer(Modifier.width(10.dp))
                            ArtistAvatar(item.avatar)
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    LazyRow(
                        contentPadding = PaddingValues(start = 30.dp),
                        reverseLayout = item.reverse
                    ) {
                        items(item.artwork) {
                            Box(
                                modifier = Modifier
                                    .padding(end = 20.dp)
                                    .clip(it.second)
                            ) {
                                Image(
                                    painter = painterResource(it.first),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(140.dp, 200.dp)
                                )
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.navigationBarsPadding())
        }
    }
}

@Composable
fun ArtistAvatar(avatar: Int) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
    ) {
        Image(
            painter = painterResource(avatar),
            contentDescription = null, contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ArtistInfo(name: String, lifeTime: String, reverse: Boolean = false) {
    Column(horizontalAlignment = if (reverse) Alignment.End else Alignment.Start) {
        Text(name, fontSize = 25.sp, color = PrimaryDark)
        Text(lifeTime, color = Color.Gray)
    }
}