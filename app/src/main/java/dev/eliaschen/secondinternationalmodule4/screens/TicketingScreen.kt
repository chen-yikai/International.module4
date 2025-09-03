package dev.eliaschen.secondinternationalmodule4.screens

import android.icu.util.Calendar.WeekData
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.secondinternationalmodule4.R
import dev.eliaschen.secondinternationalmodule4.ui.theme.dark
import dev.eliaschen.secondinternationalmodule4.ui.theme.gray
import dev.eliaschen.secondinternationalmodule4.ui.theme.playFair
import dev.eliaschen.secondinternationalmodule4.ui.theme.yellow
import java.text.DateFormatSymbols
import java.time.DayOfWeek
import java.util.Locale

@Composable
fun TicketingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(dark)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            ), modifier = Modifier.weight(1f)
        ) {
            item {
                HeroBox()
                DateToVisit()
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text("2. Number of tickets", fontSize = 25.sp, color = yellow)
                    TicketType("General Admission", "€22")
                    TicketType(
                        "Under 18s, Under 26s residents of the EEA, Museum members, Professionals",
                        "FREE"
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(yellow)
                .padding(20.dp)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total: €44", fontSize = 22.sp, color = dark)
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = dark),
                contentPadding = PaddingValues(horizontal = 26.dp, vertical = 10.dp)
            ) { Text("Checkout", color = yellow, fontFamily = playFair, fontSize = 18.sp) }
        }
    }
}

@Composable
private fun TicketType(title: String, price: String) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                title,
                color = Color.White.copy(0.7f),
                fontSize = 17.sp
            )
            Text(text = price, fontSize = 25.sp, color = yellow)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.drawBehind {
                drawLine(
                    Color.Gray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height)
                )
            }
        ) {
            var count by remember { mutableIntStateOf(0) }

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
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun DateToVisit() {
    Column(
        Modifier
            .padding(horizontal = 20.dp)
            .padding(vertical = 20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Text("1. Date to visit", fontSize = 25.sp, color = yellow)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.chevron_left),
                    contentDescription = null,
                    tint = Color.White, modifier = Modifier.size(30.dp)
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
        val days = DateFormatSymbols.getInstance(Locale.US).shortWeekdays.drop(1)
        Box(
            modifier = Modifier
                .padding(top = 10.dp)
//                    .border(1.dp, Color.Red)
//                    .fillMaxWidth()
                .height(230.dp), contentAlignment = Alignment.Center
        ) {
            val today = 13
            var selectedDay by remember { mutableIntStateOf(today) }
            LazyVerticalGrid(columns = GridCells.Fixed(7)) {
                items(days) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
//                                .border(1.dp, Color.Red),
                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(it, color = yellow)
                    }
                }
                items(30) { num ->
                    val day = num + 1

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                if (!(day < today || day % 7 == 3)) selectedDay = day
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            day.toString(), textAlign = TextAlign.Center,
                            color = if (day < today || day % 7 == 3) Color.Gray else if (selectedDay == day) yellow else Color.Unspecified
                        )
                        if (selectedDay == day) {
                            Box(
                                Modifier
                                    .size(39.dp)
                                    .border(1.dp, yellow, CircleShape)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.museum),
            contentDescription = null,
            contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(dark.copy(0.7f))
        )
        Text(
            "Official\nTicketing Service",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontFamily = playFair, lineHeight = 35.sp
        )
        Box(
            Modifier
                .offset(y = (30).dp)
                .fillMaxWidth()
//                    .border(2.dp, Color.Red)
                .height(150.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        0.0f to Color.Transparent,
                        1f to dark
                    )
                )
        )
    }
}