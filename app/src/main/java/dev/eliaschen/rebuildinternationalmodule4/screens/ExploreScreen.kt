package dev.eliaschen.rebuildinternationalmodule4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.rebuildinternationalmodule4.LocalNavController
import dev.eliaschen.rebuildinternationalmodule4.R
import dev.eliaschen.rebuildinternationalmodule4.models.Screen
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Beige
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.ContainerGray
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Dark
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.LightGray
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.Yellow
import dev.eliaschen.rebuildinternationalmodule4.ui.theme.playFair

@Composable
fun ExploreScreen() {
    val nav = LocalNavController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .padding(start = 20.dp)
                .padding(vertical = 10.dp)
        ) {
            Text("Explore", color = Beige, fontSize = 30.sp, fontFamily = playFair)
            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp), color = Color.Gray)
        }
        Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Upcoming Event", fontSize = 25.sp)
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                    nav.navTo(Screen.Ticketing)
                }) {
                    Text("Tickets", color = LightGray)
                    Icon(
                        painter = painterResource(R.drawable.chevron_right),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp),
                        tint = Color.White
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = ContainerGray),
                modifier = Modifier.padding(vertical = 15.dp)
            ) {
                Column {
                    Box(modifier = Modifier.height(250.dp)) {
                        Image(
                            painter = painterResource(R.drawable.renaissance),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Row(
                        modifier = Modifier.padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 25.dp)
                        ) {
                            Text(
                                "10",
                                fontWeight = FontWeight.Black,
                                fontSize = 25.sp,
                                fontFamily = FontFamily.Default, lineHeight = 20.sp
                            )
                            Text("OCT", fontFamily = FontFamily.Default, lineHeight = 15.sp)
                        }
                        Column(modifier = Modifier.padding(top = 15.dp)) {
                            Text("Renaissance Exhibition", fontSize = 25.sp)
                            Column(
                                modifier = Modifier.padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Text("9:00 AM")
                                    Icon(
                                        painter = painterResource(R.drawable.arrow_right_alt),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text("6:00 PM")
                                }
                                Text(
                                    "Indulge in the rich tapestry of Renaissance art",
                                    textDecoration = TextDecoration.Underline, color = Yellow
                                )
                                Text(
                                    "+33 (0)1 23 45 67 89",
                                    color = LightGray,
                                    textDecoration = TextDecoration.Underline
                                )
                            }
                        }
                    }
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Yellow),
                        shape = RoundedCornerShape(topEnd = 0.dp, topStart = 0.dp), onClick = {
                            nav.navTo(Screen.Artists)
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "Visit Gallery",
                                color = Dark,
                                fontSize = 20.sp,
                                fontFamily = playFair
                            )
                        }
                    }
                }
            }
        }
    }
}