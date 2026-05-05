package com.example.news_app.news.presentation.news_list.home_screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.news_app.news.presentation.news_list.model.NewsUI

@Composable
fun NewsItem(news: NewsUI, onNewsClick: (NewsUI) -> Unit) {
    OutlinedCard(modifier = Modifier.fillMaxSize().padding(4.dp).clickable{onNewsClick(news)},
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.outlinedCardColors(),
        elevation = CardDefaults.outlinedCardElevation(defaultElevation = 16.dp),
        border = BorderStroke(width = 1.1.dp, color = MaterialTheme.colorScheme.onBackground)
        ){
        Column(modifier= Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
            model = news.image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().height(240.dp),
            contentScale = ContentScale.Crop
        )
            Text(modifier=Modifier.padding(8.dp),text=news.title, textAlign = TextAlign.Center, fontSize = 18.sp, maxLines = 2, overflow= TextOverflow.Ellipsis, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold ))
            Text(modifier=Modifier.padding(8.dp),text=news.description, textAlign = TextAlign.Justify, fontSize = 14.sp, style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium))

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround , verticalAlignment = Alignment.CenterVertically) {
                ConvertToBadge("Author: ${news.author}")
                ConvertToBadge("At: ${news.publishedAt}")
            }
        }
    }
}



