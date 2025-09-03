package dev.eliaschen.secondinternationalmodule4.screens

import android.graphics.BitmapFactory
import android.graphics.Rect
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.secondinternationalmodule4.LocalNavController
import dev.eliaschen.secondinternationalmodule4.R
import dev.eliaschen.secondinternationalmodule4.models.Screen
import dev.eliaschen.secondinternationalmodule4.ui.theme.dark
import dev.eliaschen.secondinternationalmodule4.ui.theme.optima
import dev.eliaschen.secondinternationalmodule4.ui.theme.playFair
import dev.eliaschen.secondinternationalmodule4.ui.theme.yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistsScreen() {
    val context = LocalContext.current
    val nav = LocalNavController.current

    val backgroundBitmap =
        remember { BitmapFactory.decodeResource(context.resources, R.drawable.background) }
    val repeatedBg =
        ImageShader(backgroundBitmap.asImageBitmap(), TileMode.Repeated, TileMode.Repeated)

    Box(modifier = Modifier.background(ShaderBrush(repeatedBg))) {
        LazyColumn(contentPadding = PaddingValues(top = 40.dp), modifier = Modifier.fillMaxSize()) {
            item {
                HeaderArea()
                TabRow()
                Spacer(Modifier.height(30.dp))
            }
            items(artists) { item ->
                Column(modifier = Modifier.padding(vertical = 20.dp)) {
                    CompositionLocalProvider(LocalLayoutDirection provides if (item.reverse) LayoutDirection.Rtl else LayoutDirection.Ltr) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.clip(CircleShape)) {
                                Image(
                                    painter = painterResource(item.avatar),
                                    contentDescription = null,
                                    modifier = Modifier.size(60.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                                Text(item.name, fontSize = 25.sp, color = dark)
                                Text(item.lifeTime, color = Color.Gray)
                            }
                        }
                    }
                    LazyRow(
                        contentPadding = PaddingValues(
                            horizontal = 30.dp,
                            vertical = 25.dp
                        ), reverseLayout = item.reverse
                    ) {
                        items(item.artworks) { (artwork, shape) ->
                            Box(
                                modifier = Modifier
                                    .padding(end = 20.dp)
                                    .clip(shape)
                                    .clickable(enabled = item == artists.first(), onClick = {
                                        nav.navTo(Screen.Exhibit)
                                    })
                            ) {
                                Image(
                                    painter = painterResource(artwork),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(150.dp, 200.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TabRow() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val tabItems = listOf("Artists", "Artworks")
    PrimaryTabRow(
        selectedTabIndex = selectedIndex,
        containerColor = Color.Transparent,
        indicator = {
            TabRowDefaults.PrimaryIndicator(
                color = yellow,
                modifier = Modifier.tabIndicatorOffset(
                    selectedIndex,
                    matchContentSize = true
                ), width = 50.dp
            )
        }, divider = { HorizontalDivider(thickness = 2.dp, color = Color.Gray) }
    ) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index }) {
                Text(
                    item,
                    color = if (selectedIndex == index) yellow else Color.Gray,
                    fontSize = 25.sp, modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }
    }
}

@Composable
private fun HeaderArea() {
    Column(modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp)) {
        Text(
            "Explore the art of",
            color = dark,
            fontSize = 30.sp,
            fontFamily = playFair
        )
        Text(
            "Renaissance",
            color = yellow,
            fontSize = 45.sp,
            fontFamily = playFair, modifier = Modifier.offset(y = (-10).dp)
        )
        var searchText by remember { mutableStateOf("") }
        OutlinedTextField(
            searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Type to search…", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = null, modifier = Modifier.size(25.dp)
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.crop_free),
                    contentDescription = null, modifier = Modifier.size(25.dp)
                )
            }
        )
    }
}