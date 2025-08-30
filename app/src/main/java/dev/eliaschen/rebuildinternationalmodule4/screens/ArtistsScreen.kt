package dev.eliaschen.rebuildinternationalmodule4.screens

import android.graphics.BitmapFactory
import android.graphics.Rect
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.rebuildinternationalmodule4.LocalNavController
import dev.eliaschen.rebuildinternationalmodule4.R
import dev.eliaschen.rebuildinternationalmodule4.models.Screen
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Dark
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.LightGray
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Yellow
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.playFair

data class Artist(
    val name: String,
    val lifeTime: String,
    val avatar: Int,
    val artwork: List<Pair<Int, Shape>>,
    val reverse: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistsScreen() {
    val nav = LocalNavController.current
    val context = LocalContext.current
    val bgBitmap =
        remember { BitmapFactory.decodeResource(context.resources, R.drawable.background) }
    val bgShader = ImageShader(bgBitmap.asImageBitmap(), TileMode.Repeated, TileMode.Repeated)
    val bgBrush = ShaderBrush(bgShader)

    var searchText by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabItems = listOf("Artists", "Artworks")

    val artists = listOf(
        Artist(
            name = "Leonardo da Vinci",
            lifeTime = "1452 - 1519",
            avatar = R.drawable.leonardo_da_vinci,
            artwork = listOf(
                Pair(R.drawable.mona_lisa, CircleShape),
                Pair(
                    R.drawable.lady_ermine,
                    RoundedCornerShape(bottomEnd = 500.dp, bottomStart = 500.dp)
                ),
                Pair(
                    R.drawable.litta_madonna, RectangleShape
                )
            )
        ), Artist(
            name = "Michelangelo",
            lifeTime = "1475 - 1564",
            avatar = R.drawable.michelangelo,
            artwork = listOf(
                Pair(
                    R.drawable.delphic_sibyl,
                    RoundedCornerShape(
                        topEnd = 500.dp, topStart = 500.dp
                    )
                ),
                Pair(R.drawable.torment_of_saint_anthony, CircleShape),
                Pair(R.drawable.david, RoundedCornerShape(bottomEnd = 500.dp, bottomStart = 500.dp))
            ), reverse = true
        ), Artist(
            name = "Gustav Klimt",
            lifeTime = "1862 - 1918",
            avatar = R.drawable.gustav_klimt,
            artwork = listOf(
                Pair(
                    R.drawable.the_kiss, RoundedCornerShape(
                        topEnd = 500.dp, topStart = 500.dp
                    )
                ), Pair(R.drawable.adele_bloch_bauer, RectangleShape),
                Pair(R.drawable.lady_with_fan, CircleShape)
            )
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(bgBrush),
        contentPadding = PaddingValues(top = 20.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    "Explore the art of",
                    fontSize = 30.sp,
                    fontFamily = playFair,
                    color = Dark,
                )
                Text(
                    "Renaissance",
                    fontSize = 45.sp,
                    fontFamily = playFair,
                    color = Yellow,
                )
                Spacer(Modifier.height(20.dp))
                OutlinedTextField(
                    searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Type to search…", color = Color.Gray) },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.crop_free),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp),
                            tint = Color.Gray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp),
                            tint = Color.Gray
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            PrimaryTabRow(selectedTabIndex = selectedTab,
                containerColor = Color.Transparent,
                indicator = {
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(
                            selectedTab, matchContentSize = true
                        ),
                        color = Yellow,
                        width = Dp.Unspecified,
                    )
                },
                divider = {
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = Color.Gray,
                    )
                }) {
                tabItems.forEachIndexed { index, item ->
                    Tab(selectedTab == index, onClick = {
                        selectedTab = index
                    }) {
                        Text(
                            item,
                            modifier = Modifier.padding(10.dp),
                            fontSize = 25.sp,
                            color = if (selectedTab == index) Yellow else Color.Gray
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        itemsIndexed(artists) { index, item ->
            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .clickable(enabled = index == 0, onClick = {
                        nav.navTo(Screen.Exhibit)
                    })
            ) {
                LazyRow(
                    reverseLayout = item.reverse,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    item {
                        Box(modifier = Modifier.clip(CircleShape)) {
                            Image(
                                painter = painterResource(item.avatar),
                                contentDescription = null,
                                modifier = Modifier.size(70.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(Modifier.width(10.dp))
                        Column {
                            Text(item.name, fontSize = 25.sp, color = Dark)
                            Text(item.lifeTime, color = Color.Gray)
                        }
                    }
                }
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
                    reverseLayout = item.reverse
                ) {
                    items(item.artwork) { (image, shape) ->
                        Box(
                            modifier = Modifier
                                .padding(end = 20.dp)
                                .clip(shape)
                        ) {
                            Image(
                                painter = painterResource(image),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(180.dp)
                                    .width(130.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
        item {
            Spacer(Modifier.navigationBarsPadding())
        }
    }
}