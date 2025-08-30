package dev.eliaschen.rebuildinternationalmodule4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.rebuildinternationalmodule4.R
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Dark
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.LightGray
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Yellow
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.playFair
import java.text.DateFormatSymbols
import java.util.Locale

@Composable
fun TicketingScreen() {
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Dark)
                .weight(1f)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.museum),
                        contentDescription = null,
                        contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Dark.copy(alpha = 0.6f))
                    )
                    Box(
                        modifier = Modifier
                            .offset(y = 30.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            //                    .border(2.dp, Color.Red)
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Dark.copy(alpha = 0.75f), Dark)
                                )
                            )
                            .align(Alignment.BottomCenter)
                    )
                    Text(
                        "Official\nTicketing Service",
                        fontSize = 35.sp,
                        lineHeight = 35.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center),
                        fontFamily = playFair
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("1. Date to visit", color = Yellow, fontSize = 25.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(R.drawable.chevron_left),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                            Text("September")
                            Icon(
                                painter = painterResource(R.drawable.chevron_right),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(top = 5.dp)
                    ) {
                        val today = 13
                        var selectedDate by remember { mutableIntStateOf(today) }
                        val days = DateFormatSymbols.getInstance(Locale.US).shortWeekdays.drop(1)

                        LazyVerticalGrid(columns = GridCells.Fixed(7)) {
                            items(days) {
                                Text(
                                    it,
                                    color = Yellow,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(vertical = 5.dp)
                                )
                            }
                            items(30) { item ->
                                val day = item + 1
                                val disable = day < today || day % 7 == 3
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .alpha(if (disable) 0.5f else 1f)
                                        .clickable(!disable, onClick = {
                                            selectedDate = day
                                        }),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        day.toString(),
                                        textAlign = TextAlign.Center,
                                        color = if (selectedDate == day) Yellow else Color.Unspecified
                                    )
                                    if (selectedDate == day) {
                                        Box(
                                            modifier = Modifier
                                                .size(40.dp)
                                                .border(1.dp, Yellow, CircleShape)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                    Text("2. Number of tickets", color = Yellow, fontSize = 25.sp)
                    val tickets = listOf(
                        Pair("General Admission", "€22"),
                        Pair(
                            "Under 18s, Under 26s residents of the EEA, Museum members, Professionals",
                            "FREE"
                        )
                    )
                    tickets.forEach { (type, price) ->
                        var count by remember { mutableIntStateOf(0) }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(vertical = 10.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(type)
                                Text(price, color = Yellow, fontSize = 25.sp)
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.drawBehind {
                                    drawLine(
                                        start = Offset(0f, size.height),
                                        end = Offset(size.width, size.height),
                                        color = Color.Gray, strokeWidth = 4f
                                    )
                                }) {
                                IconButton(onClick = {
                                    if (count > 0) count--
                                }) {
                                    Icon(
                                        painter = painterResource(R.drawable.remove),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Text(count.toString())
                                IconButton(onClick = {
                                    if (count < 9) count++
                                }) {
                                    Icon(
                                        painter = painterResource(R.drawable.add),
                                        contentDescription = null, tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            item { Spacer(Modifier.navigationBarsPadding()) }
        }
        Row(
            modifier = Modifier
                .background(Yellow)
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total: €44", color = Dark, fontSize = 25.sp)
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Dark),
                onClick = {}) {
                Text(
                    "Checkout",
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
                    color = Yellow
                )
            }
        }
    }
}