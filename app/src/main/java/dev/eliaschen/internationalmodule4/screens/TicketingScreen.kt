package dev.eliaschen.internationalmodule4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.internationalmodule4.R
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryDark
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryGray
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryYellow
import dev.eliaschen.internationalmodule4.ui.theme.playFair
import java.text.DateFormatSymbols
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun TicketingScreen() {
    val scroller = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryDark)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scroller)
                .weight(1f)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.height(300.dp)) {
                Image(
                    painter = painterResource(R.drawable.museum),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PrimaryDark.copy(alpha = 0.8f))
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Transparent, PrimaryDark
                                )
                            )
                        )
                )
                Text(
                    "Official\nTicketing Service",
                    fontSize = 35.sp,
                    fontFamily = playFair,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp
                )
            }
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Row {
                    Text("1. Date to visit", fontSize = 25.sp, color = PrimaryYellow)
                    Spacer(Modifier.weight(1f))
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
                val weekDays = DateFormatSymbols(Locale.US).shortWeekdays.drop(1)
                val today = 13
                var selectedDate by remember { mutableStateOf(today) }
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        verticalArrangement = Arrangement.Center,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        items(weekDays) {
                            Text(
                                it,
                                color = PrimaryYellow,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                        items(30) {
                            val day = it + 1
                            val disable = day < today || day % 7 == 3
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        if (!disable) selectedDate = day
                                    }, contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(35.dp)
                                        .then(
                                            if (day == selectedDate) Modifier.border(
                                                1.dp, PrimaryYellow,
                                                CircleShape
                                            ) else Modifier
                                        )
                                )
                                Text(
                                    day.toString(),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier,
                                    color = if (disable) PrimaryGray.copy(alpha = 0.5f) else if (day == selectedDate) PrimaryYellow else Color.White
                                )
                            }
                        }
                    }
                }
                Text("2. Number of tickets", fontSize = 25.sp, color = PrimaryYellow)
                TickingQuantity("General Admission", "€22")
                TickingQuantity(
                    "Under 18s, Under 26s residents of the EEA, Museum members, Professionals",
                    "FREE"
                )

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryYellow)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Total: €44", color = PrimaryDark, fontSize = 25.sp)
                    Card(
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(containerColor = PrimaryDark), onClick = {}
                    ) {
                        Text(
                            "Checkout",
                            color = PrimaryYellow,
                            modifier = Modifier.padding(horizontal = 30.dp, vertical = 10.dp)
                        )
                    }
                }
                Spacer(Modifier.navigationBarsPadding())
            }
        }
    }
}

@Composable
fun TickingQuantity(type: String, price: String) {
    var quantity by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier
            .weight(1f)
            .padding(end = 20.dp)) {
            Text(type, color = PrimaryGray)
            Text(price, fontSize = 25.sp, color = PrimaryYellow)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.drawBehind {
                drawLine(
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    color = PrimaryGray
                )
            }
        ) {
            IconButton(onClick = {
                if (quantity > 0) {
                    quantity--
                }
            }) {
                Icon(
                    painter = painterResource(R.drawable.remove),
                    contentDescription = null,
                    tint = Color.White, modifier = Modifier.size(20.dp)
                )
            }
            Text(quantity.toString())
            IconButton(onClick = {
                if (quantity < 9) {
                    quantity++
                }
            }) {
                Icon(
                    painter = painterResource(R.drawable.add), contentDescription = null,
                    tint = Color.White, modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
