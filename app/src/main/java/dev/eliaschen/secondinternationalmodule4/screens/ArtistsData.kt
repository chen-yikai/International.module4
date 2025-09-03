package dev.eliaschen.secondinternationalmodule4.screens

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.google.gson.annotations.SerializedName
import dev.eliaschen.secondinternationalmodule4.R


val bottomRounded = RoundedCornerShape(bottomEnd = 500.dp, bottomStart = 500.dp)
val topRounded = RoundedCornerShape(topEnd = 500.dp, topStart = 500.dp)
val artists = listOf(
    Artist(
        name = "Leonardo da Vinci",
        lifeTime = "1452 - 1519",
        avatar = R.drawable.leonardo_da_vinci,
        artworks = listOf(
            Pair(R.drawable.mona_lisa, CircleShape),
            Pair(R.drawable.lady_ermine, bottomRounded),
            Pair(R.drawable.litta_madonna, RectangleShape)
        )
    ),
    Artist(
        name = "Michelangelo",
        lifeTime = "1475 - 1564",
        avatar = R.drawable.michelangelo,
        artworks = listOf(
            Pair(R.drawable.delphic_sibyl, topRounded),
            Pair(R.drawable.torment_of_saint_anthony, CircleShape),
            Pair(R.drawable.david, bottomRounded)
        ),
        reverse = true
    ),
    Artist(
        name = "Gustav Klimt",
        lifeTime = "1862 - 1918",
        avatar = R.drawable.gustav_klimt,
        artworks = listOf(
            Pair(R.drawable.the_kiss, topRounded),
            Pair(R.drawable.adele_bloch_bauer, RectangleShape),
            Pair(R.drawable.lady_with_fan, CircleShape)
        )
    )
)

data class Artist(
    val name: String,
    val lifeTime: String,
    val avatar: Int,
    val artworks: List<Pair<Int, Shape>>,
    val reverse: Boolean = false
)

data class Artwork(
    val title: String,
    val years: String,
    @SerializedName("born_at") val bornAt: String,
    val comment: String
)