package com.example.news_app.news.presentation.news_list.home_screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ConvertToBadge(text: String){
    Badge(modifier = Modifier.padding(4.dp),contentColor = MaterialTheme.colorScheme.onTertiaryContainer,containerColor = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.85f)){
        Text(text,textAlign = TextAlign.Center, fontSize = 13.sp, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
    }
}