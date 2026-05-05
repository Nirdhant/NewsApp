package com.example.news_app.news.presentation.news_list.home_screen.component


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.news_app.news.presentation.news_list.home_screen.NewsState
import com.example.news_app.news.presentation.news_list.home_screen.NewsViewModel
import com.example.news_app.news.presentation.news_list.model.NewsUI
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLazyColumn(state: NewsState, newsViewModel: NewsViewModel, onNewsClick: (NewsUI) -> Unit){
    val pullRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(isRefreshing = state.isLoading,
        onRefresh = { newsViewModel.loadNews() },
        state=pullRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier.padding(top=20.dp).align(Alignment.TopCenter).size(40.dp),
                isRefreshing = state.isLoading,
                state = pullRefreshState,
                containerColor = Color.White,
                color = Color.Red,
                threshold = 50.dp
            )
        }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
           itemsIndexed(state.news,key ={ index, news ->news.id}){index,news->
               SwipeToDeleteContainer(onSwipeToDelete = { newsViewModel.onDelete(index) }){
                   NewsItem(news, onNewsClick)
               }
           }
        }
    }
}

@Composable
fun SwipeToDeleteContainer(
    onSwipeToDelete:()->Unit,
    actualContent:@Composable ()->Unit
){
   var isRemoved  by remember {  mutableStateOf(false) }

    val swipeToDeleteState= rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart){
                isRemoved = true
                true  //assign
            }
            else  false
        }
    )
    //-----------------------------Animation Part-------------------------------------
    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved){
            delay(500L)
            onSwipeToDelete()
        }
    }
    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(animationSpec = tween(550), shrinkTowards = Alignment.Top)+ fadeOut()
    ){
        SwipeToDismissBox(
            state =swipeToDeleteState,
            backgroundContent = { SwipeToDeleteBackground() },
            content = {actualContent()},
            enableDismissFromStartToEnd = false
        )
    }
}
@Composable
fun SwipeToDeleteBackground(){
    Box(modifier = Modifier.fillMaxSize().padding(4.dp), contentAlignment = Alignment.Center){
        Row(modifier=Modifier.fillMaxWidth() ,verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            HorizontalDivider(modifier = Modifier.weight(1f), thickness = 3.dp, color = MaterialTheme.colorScheme.errorContainer)
            Icon(modifier=Modifier.size(40.dp),imageVector = Icons.Default.Delete,contentDescription = null,tint= MaterialTheme.colorScheme.errorContainer)
        }
    }
}
