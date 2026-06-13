package com.example.news.news.presentation.news_list.search_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.news.news.presentation.news_list.home_screen.component.NewsItem
import com.example.news.news.presentation.news_list.model.NewsUI

@Composable
fun SearchScreen(
    searchState: SearchState,
    searchViewModel: SearchViewModel,
    isUIVisible: Boolean,
    onNewsClick: (NewsUI) -> Unit
) {
    val query = remember { mutableStateOf(searchState.searchQuery) }
    Column(
        modifier = Modifier.statusBarsPadding().fillMaxSize()
        , horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Top
    ) {
        AnimatedVisibility(
            visible = isUIVisible,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            OutlinedTextField(
            value = query.value,
            onValueChange = {
                query.value = it
                if (it.isNotBlank() && it.length >= 3) {
                    searchViewModel.search(it)
                }
            },
            label = { Text("Search News") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .displayCutoutPadding().navigationBarsPadding()
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        )
    }
        Box(modifier = Modifier.padding(top = 4.dp).fillMaxSize()){
            if (searchState.isLoading) { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) }
            else{
                LazyColumn(modifier = Modifier
                    .navigationBarsPadding()
                    .displayCutoutPadding()
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(searchState.news, key = { index, news -> news.id }) { index, news ->
                        NewsItem(news, onNewsClick)
                    }
                }
            }
        }
    }
}