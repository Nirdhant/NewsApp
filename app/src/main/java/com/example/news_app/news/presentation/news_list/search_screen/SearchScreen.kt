package com.example.news_app.news.presentation.news_list.search_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.news_app.news.presentation.news_list.home_screen.component.NewsItem
import com.example.news_app.news.presentation.news_list.model.NewsUI

@Composable
fun SearchScreen(searchState: SearchState,searchViewModel: SearchViewModel, onNewsClick: (NewsUI) -> Unit) {
    val query = remember { mutableStateOf(searchState.searchQuery) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = query.value,
            onValueChange = {
                query.value = it
                if (it.isNotBlank() && it.length >= 3){ searchViewModel.search(it) }
            },
            label = { Text("Search News") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier.padding(top = 4.dp).fillMaxSize()){
            if (searchState.isLoading) { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) }
            else{
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(searchState.news, key = { index, news -> news.id }) { index, news ->
                        NewsItem(news, onNewsClick)
                    }
                }
            }
        }
    }
}