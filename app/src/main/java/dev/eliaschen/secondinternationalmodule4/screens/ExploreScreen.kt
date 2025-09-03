package dev.eliaschen.secondinternationalmodule4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.secondinternationalmodule4.LocalNavController
import dev.eliaschen.secondinternationalmodule4.R
import dev.eliaschen.secondinternationalmodule4.models.Screen
import dev.eliaschen.secondinternationalmodule4.ui.theme.beige
import dev.eliaschen.secondinternationalmodule4.ui.theme.dark
import dev.eliaschen.secondinternationalmodule4.ui.theme.gray
import dev.eliaschen.secondinternationalmodule4.ui.theme.playFair
import dev.eliaschen.secondinternationalmodule4.ui.theme.yellow

@Composable
fun ExploreScreen() {
    val nav = LocalNavController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(dark)
            .systemBarsPadding()
            .padding(vertical = 20.dp)
    ) {
        Column(modifier = Modifier.padding(start = 20.dp, bottom = 20.dp)) {
            Text(
                "Explore",
                fontSize = 35.sp,
                color = beige,
                fontFamily = playFair,
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 15.dp))
        }
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text("Upcoming Event", fontSize = 25.sp)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { nav.navTo(Screen.Ticketing) }) {
                    Text("Tickets", color = Color.Gray)
                    Image(
                        painter = painterResource(R.drawable.chevron_right),
                        contentDescription = null, modifier = Modifier.size(25.dp)
                    )
                }
            }
            Card(colors = CardDefaults.cardColors(containerColor = gray)) {
                Image(
                    painter = painterResource(R.drawable.renaissance),
                    contentDescription = null,
                    modifier = Modifier.height(250.dp),
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            "10",
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Default,
                            fontSize = 25.sp
                        )
                        Text("OCT")
                    }
                    Column {
                        Text("Renaissance Exhibition", fontSize = 25.sp)
                        Column(
                            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("9:00 AM")
                                Image(
                                    painter = painterResource(R.drawable.arrow_right_alt),
                                    contentDescription = null, modifier = Modifier.size(20.dp)
                                )
                                Text("6:00 PM")
                            }
                            Text(
                                "Indulge in the rich tapestry of Renaissance art",
                                textDecoration = TextDecoration.Underline, color = yellow
                            )
                            Text("+33 (0)1 23 45 67 89", textDecoration = TextDecoration.Underline)
                        }

                    }
                }
                Box(
                    modifier = Modifier
                        .clickable {
                            nav.navTo(Screen.Artists)
                        }
                        .fillMaxWidth()
                        .background(yellow)
                        .padding(20.dp)
                ) {
                    Text(
                        "Visit Gallery",
                        modifier = Modifier.align(Alignment.Center),
                        color = dark,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}