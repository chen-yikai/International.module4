package dev.eliaschen.internationalmodule4.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.eliaschen.internationalmodule4.LocalNavController
import dev.eliaschen.internationalmodule4.R
import dev.eliaschen.internationalmodule4.models.Screen
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryBeige
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryDark
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryGray
import dev.eliaschen.internationalmodule4.ui.theme.PrimaryYellow
import dev.eliaschen.internationalmodule4.ui.theme.cardBg
import dev.eliaschen.internationalmodule4.ui.theme.optima
import dev.eliaschen.internationalmodule4.ui.theme.playFair

@Composable
fun ExploreScreen() {
    val nav = LocalNavController.current

    Column(
        Modifier
            .background(PrimaryDark)
            .statusBarsPadding()
            .fillMaxSize()
            .padding(vertical = 20.dp)
    ) {
        Column(modifier = Modifier.padding(start = 20.dp)) {
            Text("Explore", color = PrimaryBeige, fontSize = 30.sp, fontFamily = playFair)
            HorizontalDivider(modifier = Modifier.padding(top = 10.dp, bottom = 20.dp))
        }
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ) {
                Text("Upcoming Event", fontSize = 25.sp)
                Row(modifier = Modifier
                    .clickable {
                        nav.navTo(screen = Screen.Ticketing)
                    }
                ) {
                    Text("Tickets", color = PrimaryGray, fontFamily = optima, fontSize = 18.sp)
                    Icon(
                        painter = painterResource(R.drawable.chevron_right),
                        contentDescription = null,
                        tint = Color.White, modifier = Modifier.size(25.dp)
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = cardBg),
                elevation = CardDefaults.elevatedCardElevation(10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.renaissance),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth, modifier = Modifier.height(250.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(20.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                    ) {
                        Text(
                            "10",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Default
                        )
                        Text("OCT", fontSize = 15.sp, fontFamily = FontFamily.Default)
                    }
                    Spacer(Modifier.width(20.dp))
                    Column {
                        Text("Renaissance Exhibition", fontSize = 25.sp)
                        Column(
                            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(7.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("9:00 AM")
                                Image(
                                    painter = painterResource(R.drawable.arrow_right_alt),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(30.dp, 10.dp)
                                        .scale(0.7f),
                                    contentScale = ContentScale.None
                                )
                                Text("6:00 PM")
                            }
                            Text(
                                "Indulge in the rich tapestry of Renaissance art",
                                textDecoration = TextDecoration.Underline, color = PrimaryYellow
                            )
                            Text(
                                "+33 (0)1 23 45 67 89",
                                textDecoration = TextDecoration.Underline,
                                color = PrimaryGray
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(PrimaryYellow)
                        .clickable {
                            nav.navTo(Screen.Artists)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Visit Gallery", fontFamily = playFair, color = cardBg, fontSize = 20.sp)
                }
            }
        }
    }
}