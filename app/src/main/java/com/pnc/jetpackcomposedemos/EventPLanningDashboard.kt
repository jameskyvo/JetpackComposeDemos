package com.pnc.jetpackcomposedemos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EventPlanningDashboard() {
    val artist = Artist(
        id = 100,
        name = "Selfie and the SimChips",
        genre = "Pop",
        location = "Miami, FL",
        imageUrl = "/images/selfiesim.jpg",
        description = "Some description of this pop group",
        tags = "Pop,Music,Modern,Dance"
    )


    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Event Planning Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )
        ArtistCard(artist = artist)
    }
}