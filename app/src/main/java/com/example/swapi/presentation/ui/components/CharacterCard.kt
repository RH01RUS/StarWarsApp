package com.example.swapi.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swapi.domain.models.Character

@Composable
fun CharacterCard(
    character: Character,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    StarWarsCard(
        onClick = onClick,
        isFavorite = character.isFavorite,
        onFavoriteClick = onFavoriteClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFFE94560), Color(0xFF533483))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = character.name.first().toString(),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = character.name.uppercase(),
                    color = Color(0xFFFFD700),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    InfoChip("${character.height}cm", "📏")
                    InfoChip("${character.mass}kg", "⚖️")
                    InfoChip(character.gender, when(character.gender.lowercase()) {
                        "male" -> "👨"
                        "female" -> "👩"
                        else -> "🤖"
                    })
                }
            }
        }
    }
}

@Composable
fun InfoChip(text: String, icon: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFF2C2C3A)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(icon, fontSize = 12.sp)
            Text(text, fontSize = 12.sp, color = Color(0xFFB0B0B0))
        }
    }
}
